SHELL = /bin/bash
.SUFFIXES :
.PHONY : all compile run cleanall

source_dir = .
bin_dir = $(source_dir)/bin

directories = $(bin_dir)

programs = $(wildcard *.java)
programs := $(programs:.java=)

programs := $(notdir $(programs))
executables = $(addprefix $(bin_dir)/, $(programs))

all : compile run
compile : $(executables)

$(directories) :
	mkdir $@

$(executables) : $(bin_dir)/% : $(source_dir)/%.java | $(bin_dir)
	gcj $(source_dir)/*.java --main=$* -o $(bin_dir)/$*

run :
	for P in $(bin_dir)/*; do echo -e "\n$$P\n"; $$P; done

cleanall :
	rm $(bin_dir)/*
