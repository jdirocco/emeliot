# About

The Emeliot Domain-Specific Language (DSL) is built with Xtext and Xbase annotations to support the failure analysis logic (FLA) on IoT systems. The language allows to specify IoT elements such as components and port, the related time-series values, and custom injections. In addition, we equipped the DSL with a set of discovery rules and an Xbase engine to generate the corresponding Java code. 


# Table of contents

- [About](#about)
- [Table of contents](#table-of-contents)
- [How to run](#how-to-run)
- [Project structure](#project-structure)
- [Modeling language](#modeling-language)
	- [Specification](#specification)
	- [Time Series](#time-series)
	- [Injection](#injection)
	- [Discovery](#discovery)
	- [Simulation](#simulation)
- [Demo](#demo)


# How to run

To open the EMF editor and generate the code and the DSL, you have to download:

- **Java version 21:** [https://adoptium.net/](https://adoptium.net/)

- **Eclipse Modeling Tools 2024-03:**

  - Download the installer from <https://www.eclipse.org/downloads/>


- **Xtext 2.36.0:** follow the download and installation instructions at <https://eclipse.dev/Xtext/download.html>


# Project structure

The project is structured as follows:

TODO

# Modeling language

The developed DSL is composed of the following concepts:

- **Model**: the root element that contains all the other concepts; 
- **Specification**: it allows the design of the IoT components, including ports and related time series;
- **Time Series**: it represent a time series specified as a tuple of numbers;
- **Injection**: it allows the specification of injection rules on the specified time series values;  
- **Discovery**: it allows the discovery function to detect a failure by comparing the injected series and the produced ones;
- **Configuration** this element ties injections with the time series (ConfigInjection) and discoveries  with expected and injected time series (ConfigDiscovery)


In addition, the DSL is able to support the simulation of the IoT component by relying on [Proteus Design Suite platform](https://www.labcenter.com/). 

The DSL is also equipped with utility functions to support the injection, detection, and simulation phases. These include functions such as to query (e.g., getMaxTime, isEmpty), visualize (e.g., printTimeSeries, reorderTimeSeries), and save/retrieve (e.g., writeInTSToFile, readInTSFromFile) a time series.  




```
Model returns Model:
    'model' name=EString ':' 
    specification=Specification
    'time_series' ':' timeSeries+=TimeSeries*
    injections+=Injection*
    discoveries+=Discovery*
    'config' ':' configurations+=Configuration*
    'simultation_platform' ':' simulationPlatform=EString
    'project_file' ':' projectFile=EString; 
  ```

During the specification, the system will provide:

- Syntax Highlighting,
- Background Validation,
- Error Markers,
- Content Assist,
- Hyperlinking, and
- Quick fixes.


## Specification

Developers can design their own IoT system by defining a set of *components*.

```
Specification returns Specification:
	{Specification}
	'specification'
('projectName' projectName=EString)?
components' ':' components+=Component*;
  ```

where a component has a list of *ports* defined as follows:

```	
Component returns Component:
	{Component}
	name=EString
	'ports' ':' ports+=Port*;

enum PORT_TYPE returns PORT_TYPE:
	INPUT='INPUT' | OUTPUT='OUTPUT';
	
EString returns ecore::EString:
	STRING | ID;


Port returns Port:
	'port' name=EString type=PORT_TYPE;
  ```
In particular, we define the type of ports using an enumeration. While this is a simplification of real-world IoT component, it provides enough information for supporting the FLA process.

## Time Series

The Time series concept is the core element of the DSL. This allows to run the simulation and a set of injections, supporting different kind of analysis.
```	
TimeSeries returns TimeSeries:
	name=EString
	('path' timeSeriesPath=STRING)?
	('[' timeValues+=TimeValue ("," timeValues+=TimeValue)* ']')?;
```


expressed as a sequence of tuple:


```	
TimeValue returns TimeValue:
	{TimeValue} 
	'(' time=EDouble ':' value=(EDouble) ')';

EDouble returns ecore::EDouble:
	'-'? INT? '.' INT (('E' | 'e') '-'? INT)?;	

```
Currently, we support only double values even though the DSL can be easily extended to support addition data types.




## Injection

The injection concept allows developers to modify the time series that feed the specified system. In particular, developers can define their own injection rules by relying on the StadardLibrary (see more [here](here)) 

```	
Injection returns Injection:
	'injection' name=EString
	operation=XBlockExpression;
```

In particular, the operation allow to write Java code leveraging the Xbase grammar.


A particular injection is mapped to a **Port** and the corresponding **Time series** using the **ConfigInjection**

```	
ConfigInjection returns ConfigMutation:
	'inj' mut=[Injection] ('port' port = EString)? 'timeSeries' timeSeries = [TimeSeries] 
 ```



The injection functions currently supported by the DSL (**41**) are the following: 

- `addTimeAndValue`: add a time-value pair to a time series  
- `addRandomTimeAndValue`: add a time-value pair to a time series, where time is random  
- `addTimeAndRandomValue`: add a time-value pair to a time series, where value is random  
- `addRandomTimeAndRandomValue`: add a time-value pair to a time series, where time and value are random  
- `addMultipleTimeValues`: add multiple time-value pairs to a time series  
- `addMultipleRandomTimeValues`: add multiple time-value pairs to a time series, where time and value are random  
- `appendTimeSeries`: append all time-value pairs from a time series to another  
- `mergeTimeSeries`: merge all time-value pairs of two time series  
- `replaceTimeSeries`: replace all time-value pairs of a time series to another  
- `changeValue`: replace the value of a time-value pair from a time series with another value  
- `changeTime`: replace the time of a time-value pair from a time series with another time  
- `changeValueWithRandom`: replace the value of a time-value pair from a time series with a random value  
- `changeTimeWithRandom`: replace the time of a time-value pair from a time series with a random time  
- `changeTimeAndValue`: replace a time-value pair from a time series with another time-value pair  
- `changeTimeWithRandomAndValue`: replace a time-value pair from a time series with another time-value pair, where time is random  
- `changeTimeAndValueWithRandom`: replace a time-value pair from a time series with another time-value pair, where value is random  
- `changeTimeWithRandomAndValueWithRandom`: replace a time-value pair from a time series with another time-value pair, where time and value are random  
- `changeARandomTimeValue`: replace a random time-value pair from a time series with another time-value pair  
- `changeARandomTimeValueWithRandomTimeValue`: replace a random time-value pair from a time series with another time-value pair, where time and value are random  
- `changeMultipleTimeValues`: replace multiple time-value pairs from a time series with another time-value pairs  
- `changeMultipleTimeValuesWithRandomTimeValues`: replace multiple time-value pairs from a time series with another time-value pairs, where time and value are random  
- `changeTimeLate`: replace the time of a time-value pair from a time series with a time exceeding the upper bound time constraint  
- `changeRandomTimeLate`: replace the time of a random time-value pair from a time series with a time exceeding the upper bound time constraint  
- `changeMultipleTimeLate`: replace the time of multiple time-value pairs from a time series with a time exceeding the upper bound time constraint  
- `changeTimeEarly`: replace the time of a time-value pair from a time series with a time exceeding the lower bound time constraint  
- `changeRandomTimeEarly`: replace the time of a random time-value pair from a time series with a time exceeding the lower bound time constraint  
- `changeMultipleTimeEarly`: replace the time of multiple time-value pairs from a time series with a time exceeding the lower bound time constraint  
- `changeValueCoarse`: replace the value of a time-value pair from a time series with a value exceeding the value constraint  
- `changeRandomValueCoarse`: replace the value of a random time-value pair from a time series with a value exceeding the value constraint  
- `changeMultipleValueCoarse`: replace the value of multiple time-value pairs from a time series with a value exceeding the value constraint  
- `changeValueSubtle`: replace the value of a time-value pair from a time series with a value within the value constraint but exceeding threshold  
- `changeRandomValueSubtle`: replace the value of a random time-value pair from a time series with a value within the value constraint but exceeding threshold  
- `changeMultipleValueSubtle`: replace the value of multiple time-value pairs from a time series with a value within the value constraint but exceeding threshold  
- `removeTimeValue`: remove a time-value pair from a time series  
- `removeRandomTimeValue`: remove a random time-value pair from a time series  
- `removeMultipleTimeValues`: remove multiple time-value pairs from a time series  
- `removeAllTimeValues`: remove all time-value pairs from a time series  
- `removeTimeValuesBeforeTime`: remove all time-value pairs with time before a given time from a time series  
- `removeTimeValuesAfterTime`: remove all time-value pairs with time after a given time from a time series  
- `removeTimeValuesBelowValue`: remove all time-value pairs with value below a given value from a time series  
- `removeTimeValuesAboveValue`: remove all time-value pairs with value above a given value from a time series  

For each function listed above, a variant that reads from and writes to files is also available, which can be called using the suffix _`_File`_.





## Discovery

The discovery concept support the analysis of the injected time series. The final objetive is to discover if any failure occurs by comparing the injected time series with the ones obtained by the simulation.

```	
Discovery returns Discovery:
	{Discovery}
	'discovery'
	name=EString
	expression=XBlockExpression;
 ```

Similar to the injection, a discovery has to be configured using the corrisponding **ConfigDiscovery** element:

```	
ConfigDiscovery returns ConfigDiscovery:
	'discovery' discovery=[Discovery] ('port' port = EString)?
	'expectedSeries' expectedTimeSeries = [TimeSeries] 
	'injectedSeries' mutatedTimeSeries = [TimeSeries];
  ```
where the developer can set the expected and injected series, allowing the comparison 

The discovery functions currently supported by the DSL (**11**) are the following: 

- `isCommission`: check if the injected time series has more time-value pairs than the the original
- `isOmission`: check if the injected time series has fewer time-value pairs than the original
- `isLate`: check if the injected time series has time-value pairs with time exceeding upper bound time constraint with respect to the original
- `isEarly`: check if the injected time series has time-value pairs with time exceeding lower bound time constraint with respect to the original
- `isValueCoarse`: check if the injected time series has time-value pairs with value exceeding value constraint with respect to the original
- `isValueSubtle`: check if the injected time series has time-value pairs with value within value constraint but exceeding threshold with respect to the original
- `areSameSize`: check if two time series have the same number of time-value pairs
- `isSmaller`: check if a time series is smaller than the other one
- `isBigger`: check if a time series is bigger than the other one
- `hasTimeOutRange`: check if a time series has time-value pairs with time exceeding time constraints
- `hasValueOutRange`: check if a time series has time-value pairs with value exceeding value constraints

For each function listed above, a variant that reads from and writes to files is also available, which can be called using the suffix _`_File`_.


## Simulation

The simulation is handled by using the last two components of the **Model** root element.

```	
'simultation_platfrom' ':' simulationPlatform=EString
'project_file' ':' projectFile=EString;
  ```

**Note:** Currently, we supported only Proteus simulation platform, even though the source code can be easily adapted to support other simulation services. 

The simulation is performed by the `runTestCase` method, which runs a Proteus istance via simulation graphs, taking input time series from files to feed input ports and saving output time series to file from output ports 


# Demo




















