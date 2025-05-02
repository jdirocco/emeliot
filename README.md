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


In addition, the DSL is able to support the simulation of the IoT component by relying on [Proteus Design Suite platform](https://www.labcenter.com/) 


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

where a component is defined as follows:

```	
Component returns Component:
	{Component}
	name=EString
	'ports' ':' ports+=Port*;

enum PORT_TYPE returns PORT_TYPE:
	INPUT='INPUT' | OUTPUT='OUTPUT';
	
EString returns ecore::EString:
	STRING | ID;

EDouble returns ecore::EDouble:
	'-'? INT? '.' INT (('E' | 'e') '-'? INT)?;	

Port returns Port:
	'port' name=EString type=PORT_TYPE;
  ```


## Time Series


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


## Injection


```	
Injection returns Injection:
	'injection' name=EString
	operation=XBlockExpression;
```
A particular injection is mapped to a **Port** and the corresponding **Time series** using the **ConfigInjection**

```	
ConfigInjection returns ConfigMutation:
	'inj' mut=[Injection] ('port' port = EString)? 'timeSeries' timeSeries = [TimeSeries] 
 ```


## Discovery



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

## Simulation

The simulation is handled by using the last two components of the **Model** root element.

```	
'simultation_platfrom' ':' simulationPlatform=EString
'project_file' ':' projectFile=EString;
  ```

**Note:** Currently, we supported only Proteus simulation platform, even though the source code can be easily adapted to support other simulation services. 

# Demo




















