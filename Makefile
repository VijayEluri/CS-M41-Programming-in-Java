.SUFFIXES :

language = java

chapter = 1
section = 1

# #########################################

base_bin_dir = bin
ifeq ($(language),java)
bin_dir = $(base_bin_dir)/Java
else
bin_dir = $(base_bin_dir)/C++
endif

directories = $(bin_dir)

source_dir = Chapter$(chapter)/Section$(section)

ifeq ($(language),java)
programs = $(wildcard $(source_dir)/*.java)
programs := $(programs:.java=)
else
programs = $(wildcard $(source_dir)/*.cpp)
programs := $(programs:.cpp=)
endif
programs := $(notdir $(programs))
executables = $(addprefix $(bin_dir)/, $(programs))


all : $(executables)

ifeq ($(language),java)
$(executables) : $(bin_dir)/% : $(source_dir)/%.java | $(bin_dir)
	gcj $(source_dir)/$*.java --main=$* -o $(bin_dir)/$* -I Chapter1/InputOutput
else
$(executables) : $(bin_dir)/% : $(source_dir)/%.cpp | $(bin_dir)
	g++ $(source_dir)/$*.cpp -o $(bin_dir)/$*
endif

$(directories) :
	mkdir -p $@

