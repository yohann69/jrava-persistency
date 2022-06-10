# Jrava

Sample Java application with GUI, used to learn/practice OOD/OOP.

## What is Jrava?

Jrava is a standalone application used to **track sport activities** (limited to _running_, _swimming_ and _riding_).

An **activity** is described by:

- a *date*,
- a *type*,
- a *title*, 
- a *duration* (in seconds), 
- a *distance* (in meters) 
- a more detailed but optional *description*.

## Application requirements

The application is **graphical** (*master/detail*) and executed in **single user mode**.

It allows to:

- **create a new activity**
    - a *unique id* is then associated to the created activity for further selection
- **display a brief of activities** (id, date, type, title, duration, distance) -> _master view_
    - briefs are displayed as a table, one activity per line
    - by default, all activities are displayed but it is possible to apply a filter in order to see only a specific type of activity
- **display an activity** (selected by its id) -> _detail view_
	- it is also possible to **update activity title and description**
	
- **display statistical data** about activities :
	- total activity count,
	- monthlty and yearly total count, duration and distance.
    - by default, statistical data are computed on all activities, but it is possible to apply a filter in order to compute only for specific type of activity

Activities that are created/updated using the application **are made persitant** across executions (stored at creation/update, loaded at startup).

