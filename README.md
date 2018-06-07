# spark_template_project.g8
This template help to create a projects with 2 modules:
- common : wich contains spark tools
- your modue: wich use the spark tools 

To use this template:

1) install giter8 on your machine

2) use the follow command to create your project with one module:

- if you clone the project in your own directory
g8 file://spark_template_project.g8/  --name="project name" --projectModule="module name" --organization=your.package.to.create


- you can directly create the project from bitbucket
g8 https://github.com/mangogBasile/spark_template_project.g8   --name="project name" --projectModule="module name" --organization=your.package.to.create
