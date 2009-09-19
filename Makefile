.SUFFIXES :

language = java

base_bin_dir = bin
ifeq ($(language),java)
bin_dir = $(base_bin_dir)/Java
else
bin_dir = $(base_bin_dir)/C++
endif

directories = $(bin_dir)

programs = HelloWorld UseArgument
executables = $(addprefix $(bin_dir)/, $(programs))


all : $(executables)

ifeq ($(language),java)
$(executables) : $(bin_dir)/% : %.java | $(bin_dir)
	gcj $*.java --main=$* -o $(bin_dir)/$*
else
$(executables) : $(bin_dir)/% : %.cpp | $(bin_dir)
	g++ $*.cpp -o $(bin_dir)/$*
endif

$(directories) :
	mkdir -p $@

