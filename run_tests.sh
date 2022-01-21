#!/bin/bash

function compile
{
	javac calc/*.java && javac ui/*.java &&  javac -cp .:junit-4.10.jar test/*.java
}

function run
{
	java -cp .:junit-4.10.jar org.junit.runner.JUnitCore test.TestJUnit
}

function main
{
	if [ "$1" == "compile" ] ; then
		compile
		exit 0
	fi
	compile && run
}


main $@
