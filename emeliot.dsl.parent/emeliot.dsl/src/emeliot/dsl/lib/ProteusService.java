package emeliot.dsl.lib;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import emeliot.dsl.read.ReadFactory;
import emeliot.dsl.read.TimeSeries;
import emeliot.dsl.read.TimeValue;

public class ProteusService extends EmeliotLib {

	
	

	@Override
	public void writeInTSToFile(TimeSeries s, String filePath) throws IOException {
		if (!(s instanceof TimeSeries))
			throw new ClassCastException("TimeSeries is not an instance of TimeSeriesValue");
		reorderTimeSeries(s);
		List<String> lines = ((TimeSeries) s).getTimeValues().stream().map(tv -> tv.getTime() + " " + tv.getValue()).collect(Collectors.toList());
		Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	@Override
	public TimeSeries readInTSFromFile(String filePath) throws IOException {
		TimeSeries TimeSeriesValue = ReadFactory.eINSTANCE.createTimeSeries();
		List<String> lines = Files.readAllLines(Paths.get(filePath));
		for (String line : lines) {
			String[] parts = line.trim().split("\\s+");
			if (parts.length != 2)
				throw new IOException("Invalid line format: " + line);
			TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
			tv.setTime(Double.parseDouble(parts[0].trim()));
			double value = (Double.parseDouble(parts[1].trim()));
			tv.setValue(value);
			TimeSeriesValue.getTimeValues().add(tv);
		}
		reorderTimeSeries(TimeSeriesValue);
		return TimeSeriesValue;
	}
	
	@Override
	public TimeSeries readOutTSFromFile(String filePath) throws IOException {
	    TimeSeries timeSeriesValue = ReadFactory.eINSTANCE.createTimeSeries();
	    List<String> lines = Files.readAllLines(Paths.get(filePath));
	    if (lines.isEmpty())
	        throw new IOException("Invalid file format: file is empty");
	    int startIndex = 0;
	    if (lines.get(0).trim().toUpperCase().startsWith("\"TIME\""))
	        startIndex = 1; // Skip header if present
	    for (int i = startIndex; i < lines.size(); i++) {
	        String line = lines.get(i).trim();
	        if (line.isEmpty()) 
	        	continue; // Skip empty lines
	        String[] parts = line.split(",");
	        if (parts.length != 2)
	            throw new IOException("Invalid line format: " + line);
	        try {
	            double time = Double.parseDouble(parts[0].trim());
	            double value = Double.parseDouble(parts[1].trim());
	            TimeValue tv = ReadFactory.eINSTANCE.createTimeValue();
	            tv.setTime(time);
	            tv.setValue(value);
	            timeSeriesValue.getTimeValues().add(tv);
	        } catch (NumberFormatException e) {
	            throw new IOException("Invalid numeric format in line: " + line, e);
	        }
	    }
	    reorderTimeSeries(timeSeriesValue);
	    return timeSeriesValue;
	}
	
	

	

	
	
	/********************TODO: SIMULATION OPERATORS (Proteus)********************/
	
	//this method runs a Proteus component via smulation graphs, using input time series to produce output times series from output ports 
	public void runTestCase(Path componentPath, Path inputTSPath, Path outputTSPath, List<String> outputPorts) throws Exception {
		
		//params checks
		if(componentPath == null || inputTSPath == null || outputTSPath == null ||
				!(Files.exists(componentPath)) || !(Files.exists(inputTSPath)) || !(Files.exists(outputTSPath)))
			throw new Exception("A file path does not exist");
		if(outputPorts.isEmpty()||outputPorts.size()>9)
			throw new Exception("Number of output ports is out of range [1-9])"); //up to 9 output ports are supported by the UI
																				  //as the simulation via UI enables the interaction with up to 9 graphs
	
		//backup original component timeseries (if any)
		backupOriginalTS(componentPath);
		
        //copy input timeseries from inputTSPath to componentPath
		moveInputTS(componentPath, inputTSPath);        
        
        //execute component on Proteus using input time series
        runProteus(componentPath, outputPorts);
        
        //save output timeseries generated by output ports from componentPath to outputTSPath
        moveOutputTS(componentPath, outputTSPath);
        
        //restore component state for next execution, removing any file originated from last execution
        restoreOriginalTS(componentPath);	        

	}
	
	//this method performs a backup of the original timeseries (.txt) feeding the input ports of a Proteus component
	private static void backupOriginalTS(Path componentPath) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(componentPath)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString();
                if (fileName.toLowerCase().endsWith(".txt") && !fileName.contains("_backup")) {
                    String newFileName = fileName.replace(".txt", "_backup" + ".txt");
                    Path newFilePath = file.resolveSibling(newFileName);
                    Files.move(file, newFilePath);
                }
            }
        }
	}
	
	//this method moves the input timeseries into the Proteus component folder, to be injected to input ports
	private static void moveInputTS(Path componentPath, Path inputTSPath) throws IOException {
        Files.walkFileTree(inputTSPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path destFile = componentPath.resolve(inputTSPath.relativize(file));
                Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path destDir = componentPath.resolve(inputTSPath.relativize(dir));
                if (Files.notExists(destDir)) {
                    Files.createDirectory(destDir);
                }
                return FileVisitResult.CONTINUE;
            }
        });
	}
	

	//this method runs Proteus simulation for the component.
	//it uses output ports names to save output files once the simulation is completed


	@SuppressWarnings("deprecation")
	private void runProteus(Path componentPath, List<String> outputPorts) throws Exception {
		//input checks
	    Path component = Files.find(componentPath, 1, (path, attrs) -> path.toString().endsWith(".pdsprj"))
	            .findFirst()
	            .orElse(null);
		if (component == null)
	        throw new FileNotFoundException("No .pdsprj file found in " + componentPath.toString());  
	    //start Proteus
		long WAIT_TIME = 5000;
	    Thread.sleep(WAIT_TIME);
	    System.out.println("RUNNING PROTEUS TEST CASE " + componentPath.getFileName() + " >>>");
	    Runtime.getRuntime().exec("cmd /c start " + component.toString());
	    Thread.sleep(2 * WAIT_TIME);
	    Robot robot = new Robot();
	    for (int i = 0; i < outputPorts.size(); i++) { //for each output port, it interacts with graph via menu
	        try {
	    		//open Graph Menu 
	        	Thread.sleep(WAIT_TIME);
	            robot.keyPress(KeyEvent.VK_ALT);
	            robot.keyPress(KeyEvent.VK_G);
	            robot.keyRelease(KeyEvent.VK_G);
	            robot.keyRelease(KeyEvent.VK_ALT);
	            Thread.sleep(WAIT_TIME);
	    		//digit i to focus on graph associated with probe/output port i 
	            switch(i) {
	            	case 0: {robot.keyPress(KeyEvent.VK_1);robot.keyRelease(KeyEvent.VK_1); break;}
	            	case 1: {robot.keyPress(KeyEvent.VK_2);robot.keyRelease(KeyEvent.VK_2); break;}
	            	case 2: {robot.keyPress(KeyEvent.VK_3);robot.keyRelease(KeyEvent.VK_3); break;}
	            	case 3: {robot.keyPress(KeyEvent.VK_4);robot.keyRelease(KeyEvent.VK_4); break;}
	            	case 4: {robot.keyPress(KeyEvent.VK_5);robot.keyRelease(KeyEvent.VK_5); break;}
	            	case 5: {robot.keyPress(KeyEvent.VK_6);robot.keyRelease(KeyEvent.VK_6); break;}
	            	case 6: {robot.keyPress(KeyEvent.VK_7);robot.keyRelease(KeyEvent.VK_7); break;}
	            	case 7: {robot.keyPress(KeyEvent.VK_8);robot.keyRelease(KeyEvent.VK_8); break;}
	            	case 8: {robot.keyPress(KeyEvent.VK_9);robot.keyRelease(KeyEvent.VK_9); break;}
	            	default: {throw new IllegalArgumentException("Too many output ports!");}
	            }
	            //start simulation via space bar
	    	    robot.keyPress(KeyEvent.VK_SPACE);
	            robot.keyRelease(KeyEvent.VK_SPACE);
	            Thread.sleep(5*WAIT_TIME);
	            //open Graph menu and select Export data option
	            robot.keyPress(KeyEvent.VK_ALT);
	            robot.keyPress(KeyEvent.VK_G);
	            robot.keyPress(KeyEvent.VK_DOWN);robot.keyRelease(KeyEvent.VK_DOWN);
	            robot.keyPress(KeyEvent.VK_DOWN);robot.keyRelease(KeyEvent.VK_DOWN);
	            robot.keyPress(KeyEvent.VK_DOWN);robot.keyRelease(KeyEvent.VK_DOWN);
	            robot.keyPress(KeyEvent.VK_DOWN);robot.keyRelease(KeyEvent.VK_DOWN);
	            robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_G);
	            robot.keyRelease(KeyEvent.VK_ALT);
	            Thread.sleep(WAIT_TIME);
	            //save output file as the name of the output port i

	            StringSelection stringSelection = new StringSelection(outputPorts.get(i));
	            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	            clipboard.setContents(stringSelection, stringSelection);
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);
	            Thread.sleep(WAIT_TIME);
	            //close the Graph window
	            robot.keyPress(KeyEvent.VK_ESCAPE);robot.keyRelease(KeyEvent.VK_ESCAPE);
	            Thread.sleep(WAIT_TIME);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    //close Proteus
	    Runtime.getRuntime().exec("cmd /c taskkill /F /IM PDS.exe");

	    System.out.println("PROTEUS TEST CASE " + componentPath.getFileName() + " COMPLETED.");
	}
	
	//this method saves the outputs to outputsPath folder once a simulation is completed
	private static void moveOutputTS(Path componentPath, Path outputTSPath) throws IOException {
	    Files.walkFileTree(componentPath, new SimpleFileVisitor<Path>() {
	        @Override
	        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	            String fileName = file.getFileName().toString().toLowerCase();
	            if (fileName.endsWith(".dat")) {
	                Path destFile = outputTSPath.resolve(componentPath.relativize(file));
	                Files.createDirectories(destFile.getParent());
	                Files.move(file, destFile, StandardCopyOption.REPLACE_EXISTING);
	            }
	            return FileVisitResult.CONTINUE;
	        }
	        @Override
	        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
	            return FileVisitResult.CONTINUE;
	        }
	    });
	}
	
	//this method cleans the component folder from any file generated by a simulation and restore the original timeseries
	public static void restoreOriginalTS(Path componentPath) throws IOException {
        String[] extensions = {".txt", ".csv", ".DAT", ".workspace"};
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(componentPath)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString().toLowerCase();
                boolean isBackupFile = fileName.contains("_backup");
                if (!isBackupFile) {
                    for (String ext : extensions) {
                        if (fileName.toLowerCase().endsWith(ext.toLowerCase())) {
                            Files.delete(file);
                            break;
                        }
                    }
                }
            }
        }
        Path backupDir = componentPath.resolve("Project Backups");
        if (Files.exists(backupDir)) {
            Files.walk(backupDir)
                .sorted(Comparator.reverseOrder())
                .forEach(p -> {
                    try {
                        Files.delete(p);
                    } catch (IOException e) {
                        System.err.println("Error deleting file: " + p + " - " + e.getMessage());
                    }
                });
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(componentPath)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString();
                if (fileName.contains("_backup")) {
                    String originalFileName = fileName.replace("_backup", "");
                    Path originalFilePath = file.resolveSibling(originalFileName);
                    Files.move(file, originalFilePath);
                }
            }
        } 
    }

	
}
