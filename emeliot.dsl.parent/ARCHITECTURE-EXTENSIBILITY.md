# Emeliot Framework: Platform Extensibility Architecture

## Overview

The Emeliot framework employs a **Strategy Pattern** combined with **Code Generation** to achieve platform independence and extensibility for embedded system simulation environments.

## Architecture Layers

### 1. **Service Interface Layer**

Three core interfaces define the contract for platform implementations:

- **`EmeliotService`**: ~50 query and manipulation operations for time series
  - Examples: `getAllTimes()`, `getValueAt()`, `readInTSFromFile()`, `writeInTSToFile()`

- **`EmeliotMutationService`**: ~100 fault injection operators
  - Examples: `changeValue()`, `changeTimeLate()`, `changeValueCoarse()`, `addTimeAndValue()`

- **`EmeliotDiscoveryService`**: ~20 anomaly detection operators
  - Examples: `isCommission()`, `isLate()`, `isValueCoarse()`, `hasValueOutRange()`

### 2. **Base Implementation Layer**

```
EmeliotLib (abstract base class)
    ├── Common utility methods
    └── Foundation for platform-specific services
```

### 3. **Platform-Specific Implementation Layer**

Each simulation platform has a dedicated service class:

#### **ProteusService** (Current Implementation)
```java
public class ProteusService extends EmeliotLib
    implements EmeliotService, EmeliotMutationService, EmeliotDiscoveryService {

    // Platform-specific methods
    public void runProteus(Path componentPath, List<String> outputPorts)
    public void runTestCase(Path componentPath, Path inputTSPath,
                           Path outputTSPath, List<String> outputPorts)

    // File I/O adapted for Proteus format
    public TimeSeries readInTSFromFile(String filePath)  // Space-separated: "time value"
    public TimeSeries readOutTSFromFile(String filePath) // CSV with header
    public void writeInTSToFile(TimeSeries s, String filePath)

    // Automation internals
    private void backupOriginalTS(Path componentPath)
    private void moveInputTS(Path componentPath, Path inputTSPath)
    private void moveOutputTS(Path componentPath, Path outputTSPath)
    private void restoreOriginalTS(Path componentPath)
}
```

**Implementation Details:**
- GUI automation using `java.awt.Robot` for keyboard control
- File format conversion (input: `time value`, output: `"TIME","port"\ntime,value`)
- State management (backup/restore component files)

#### **Future Platform Examples**

```java
// Tinkercad Circuits (REST API-based)
public class TinkercadService extends EmeliotLib {
    public void runTinkercad(String projectId, List<String> outputPorts)
    // REST API calls to Autodesk Tinkercad
    // JSON-based time series serialization
}

// ModelSim (CLI-based HDL simulator)
public class ModelSimService extends EmeliotLib {
    public void runModelSim(Path doFile, List<String> signalNames)
    // CLI automation using ProcessBuilder
    // .do script generation and waveform parsing
}

// Hardware-in-the-Loop Testbed
public class HILTestbedService extends EmeliotLib {
    public void executeOnHardware(String devicePort, byte[] firmware)
    // Serial/UART communication
    // Real-time data acquisition
}

// Digital Twin Environment
public class DigitalTwinService extends EmeliotLib {
    public void executeOnDigitalTwin(String twinEndpoint, Map<String, Object> config)
    // WebSocket/MQTT integration
    // Real-time state synchronization
}
```

### 4. **Code Generation Layer**

The **`EmeliotJvmModelInferrer`** dynamically selects the platform service based on the `simulationPlatform` parameter in the Emeliot model:

```xtend
def dispatch void infer(Model element, IJvmDeclaredTypeAcceptor acceptor,
                        boolean isPreIndexingPhase) {
    val className = element.name

    acceptor.accept(element.toClass(className) [
        // Platform-specific superclass injection
        if ("proteus".equals(element.simulationPlatform))
            superTypes += typeRef(ProteusService)
        else if ("tinkercad".equals(element.simulationPlatform))
            superTypes += typeRef(TinkercadService)
        else if ("modelsim".equals(element.simulationPlatform))
            superTypes += typeRef(ModelSimService)
        else if ("hil".equals(element.simulationPlatform))
            superTypes += typeRef(HILTestbedService)
        else
            // Default fallback
            superTypes += typeRef(EmeliotLib)

        // Generate injection methods
        for (mut : element.injections) {
            members += mut.toMethod(mut.name, typeRef(Void.TYPE)) [
                body = mut.operation
                parameters += mut.toParameter("inTS", typeRef(TimeSeries))
            ]
        }

        // Generate discovery methods
        for (disc : element.discoveries) {
            members += disc.toMethod(disc.name, typeRef(Boolean.TYPE)) [
                body = disc.expression
                parameters += disc.toParameter("expectedTS", typeRef(TimeSeries))
                parameters += disc.toParameter("outTS", typeRef(TimeSeries))
            ]
        }

        // Generate execution orchestration
        members += element.toMethod("doExecute", Void.TYPE.typeRef) [
            body = '''
                // Apply injections
                «FOR config : element.configurations.filter[e|e instanceof ConfigMutation]»
                    «(config as ConfigMutation).mut.name»(«(config as ConfigMutation).timeSeries.name»);
                    writeInTSToFile(«(config as ConfigMutation).timeSeries.name», "..._M.txt");
                «ENDFOR»

                // Run simulation (platform-specific method inherited from superclass)
                // this.runProteus(...) / this.runTinkercad(...) / etc.

                // Execute discoveries
                «FOR config : element.configurations.filter[e|e instanceof ConfigDiscovery]»
                    if («(config as ConfigDiscovery).discovery.name»(
                        «(config as ConfigDiscovery).expectedTimeSeries.name»,
                        «(config as ConfigDiscovery).mutatedTimeSeries.name»)) {
                        System.out.println("Discovery found anomaly!");
                    }
                «ENDFOR»
            '''
        ]
    ])
}
```

### 5. **Generated Runtime Code**

Example generated class for a Proteus-based model:

```java
public class IrrigationUnit_S1_I1 extends ProteusService {
    // Fields for time series
    private TimeSeries ts_c1 = ReadFactory.eINSTANCE.createTimeSeries();
    private TimeSeries ts_c2 = ReadFactory.eINSTANCE.createTimeSeries();

    // Generated injection method
    public void s1(TimeSeries inTS) {
        var times = getAllTimes(inTS);  // From EmeliotService interface
        var randomer = new java.util.Random();
        var index = randomer.nextInt(times.size-1);
        var value = getValueAt(inTS, index);  // From EmeliotService interface
        if (value < 2.5)
            changeValue(inTS, times.get(index), randomer.nextDouble(2.5,10));  // From EmeliotMutationService
        else
            changeValue(inTS, times.get(index), randomer.nextDouble(0,2.5));
    }

    // Generated discovery method
    public boolean first(TimeSeries expectedTS, TimeSeries outTS) {
        final double EPS_T = 1e-12;
        final double EPS_V = 1e-9;
        final double TH = 2.5;
        // ... discovery logic using interface methods
        return guard;
    }

    // Generated orchestration
    protected void doExecute() throws Exception {
        // Load time series
        ts_c1 = readInTSFromFile("irrigationUnit1.txt");  // From ProteusService
        ts_c2 = readInTSFromFile("irrigationUnit2.txt");

        // Apply injections
        s1(ts_c1);
        s1(ts_c2);
        writeInTSToFile(ts_c1, "ts_c1_M.txt");  // From ProteusService
        writeInTSToFile(ts_c2, "ts_c2_M.txt");

        // Run simulation
        runProteus(Path.of("component.pdsprj"), List.of("C1_out", "C2_out"));  // From ProteusService

        // Execute discoveries
        TimeSeries expected = readOutTSFromFile("original.DAT");  // From ProteusService
        TimeSeries mutated = readOutTSFromFile("mutated.DAT");
        if (first(expected, mutated)) {
            System.out.println("Discovery found anomaly!");
        }
    }

    public static void main(String[] args) {
        IrrigationUnit_S1_I1 th1s = new IrrigationUnit_S1_I1();
        try {
            th1s.doExecute();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
```

## Extensibility Workflow

### Adding a New Simulation Platform

**Step 1: Create Platform Service Class**
```java
package emeliot.dsl.lib;

public class NewPlatformService extends EmeliotLib
    implements EmeliotService, EmeliotMutationService, EmeliotDiscoveryService {

    // Implement all interface methods
    @Override
    public TimeSeries readInTSFromFile(String filePath) throws IOException {
        // Platform-specific file format parsing
    }

    @Override
    public void writeInTSToFile(TimeSeries s, String filePath) throws IOException {
        // Platform-specific file format serialization
    }

    // Platform-specific simulation execution
    public void runNewPlatform(Path projectPath, List<String> outputs) throws Exception {
        // Integration logic (API calls, CLI automation, etc.)
    }

    // Implement ~170+ interface methods from the three service interfaces
}
```

**Step 2: Update Code Generator**
```xtend
// In EmeliotJvmModelInferrer.xtend
if ("newplatform".equals(element.simulationPlatform))
    superTypes += typeRef(NewPlatformService)
```

**Step 3: Use in Emeliot Model**
```emeliot
model MyExperiment:
    specification
        components : ...

    time_series : ...

    injection myInjection { ... }

    discovery myDiscovery { ... }

    config: ...

    simultation_platfrom: newplatform  // <-- Platform selection
    project_file: "path/to/project.ext"
```

**Step 4: Generate and Execute**
```bash
# Xtext automatically regenerates Java code with NewPlatformService as superclass
# The generated class now has access to runNewPlatform() method
```

## Key Architectural Benefits

### 1. **Separation of Concerns**
- **DSL Layer**: Focus on test specification (injections, discoveries, configurations)
- **Platform Layer**: Encapsulate simulation-specific details
- **Generation Layer**: Automated orchestration code synthesis

### 2. **Type Safety**
- Interface contracts enforced at compile-time
- Xtext/Xbase ensures type-safe integration between DSL expressions and Java runtime

### 3. **Reusability**
- Standard library operations (170+ methods) work across all platforms
- Injection and discovery definitions are platform-agnostic
- Only platform selection changes in the model

### 4. **Maintainability**
- Platform implementations are isolated in separate classes
- Changes to one platform don't affect others
- Generated code is never manually edited

### 5. **Testability**
- Platform services can be unit-tested independently
- Mock implementations for testing without actual simulators
- Deterministic code generation can be validated

## Design Patterns Applied

1. **Strategy Pattern**: Platform service selection at code generation time
2. **Template Method**: `doExecute()` orchestration follows fixed workflow
3. **Factory Pattern**: `ReadFactory` for EMF model instantiation
4. **Adapter Pattern**: Platform services adapt diverse simulator APIs to common interface
5. **Facade Pattern**: Service interfaces hide complexity of time series manipulation

## Example: Platform Comparison

| Feature | Proteus | Tinkercad (Future) | ModelSim (Future) |
|---------|---------|-------------------|-------------------|
| **Integration Method** | GUI Automation (Robot) | REST API | CLI Automation |
| **Input Format** | `time value` (space-separated) | JSON | `.do` script |
| **Output Format** | `"TIME","port"\ntime,value` | JSON | `.wlf` waveform |
| **Execution** | `runProteus(Path, List<String>)` | `runTinkercad(String, List<String>)` | `runModelSim(Path, List<String>)` |
| **State Management** | File backup/restore | Cloud-based persistence | Workspace cleanup |

## Conclusion

The Emeliot extensibility architecture demonstrates a robust **separation between domain logic** (fault injection and anomaly detection) and **platform-specific execution**. The code generation approach ensures that:

1. Users write platform-agnostic test specifications in the Emeliot DSL
2. The framework automatically generates optimized, type-safe Java code
3. Platform selection is declarative (single line: `simultation_platfrom: X`)
4. New platforms can be added without modifying existing DSL models or generated code

This design enables **seamless migration** between simulation environments, **experimentation** with different tools, and **future-proofing** against evolving embedded system development toolchains—from traditional SPICE simulators to modern cloud-based digital twins and hardware-in-the-loop testbeds.


## Update (revision SoSyM, comment 2.2): platform extension point

The `if/else` platform selection described above has been replaced by an Eclipse extension point.
The inferrer no longer references any platform.

* `emeliot.dsl.lib.SimulationPlatformService`: the only platform-specific operation,
  `runSimulation(Path project, List<String> inputs, List<String> outputs)`.
* `emeliot.dsl.lib.AbstractPlatformService`: `EmeliotLib` + `SimulationPlatformService`; every platform service extends it.
* Extension point `emeliot.dsl.simulationPlatforms` (`emeliot.dsl/schema/simulationPlatforms.exsd`):
  `<platform id="..." name="..." serviceClass="..."/>`.
* `emeliot.dsl.platform.SimulationPlatformRegistry`: reads the extension registry (OSGi) and,
  outside OSGi, `META-INF/services/emeliot.dsl.platform.SimulationPlatformProvider` (ServiceLoader).
  Used by `EmeliotJvmModelInferrer` (superclass), `EmeliotValidator` (unknown platform error) and
  `EmeliotProposalProvider` (content assist).
* `doExecute()` calls `runSimulation(...)` when the model configures at least one injection.

Available platforms:

| id | service | bundle |
|---|---|---|
| `proteus` | `emeliot.dsl.lib.ProteusService` | `emeliot.dsl` |
| `javasim` | `emeliot.dsl.platform.javasim.JavaComponentService` | `emeliot.dsl.platform.javasim` |

Example: `emeliot.dsl.examples/experiments/javasim/irrigation-javasim.emeliot` with the component
`emeliot.dsl.examples/src/examples/javasim/IrrigationUnit.java`.

### Adding a platform

1. Create a plug-in that requires `emeliot.dsl`.
2. Implement a subclass of `AbstractPlatformService` (`runSimulation`, `readInTSFromFile`).
3. Contribute it in `plugin.xml` to `emeliot.dsl.simulationPlatforms`; for standalone use, also add a
   `SimulationPlatformProvider` in `META-INF/services`.
4. Add the plug-in to the dependencies of the projects that use it and set `simultation_platfrom: <id>` in the model.
