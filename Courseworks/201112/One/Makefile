SHELL = /bin/bash
.SUFFIXES :

.PHONY : all clean cleanall

programs = $(wildcard */*.java)
programs := $(programs:.java=)
classfiles := $(addsuffix .class, $(programs))

all : $(classfiles)

$(classfiles) : %.class : %.java
	- javac $<

clean :
	- rm $(classfiles)
