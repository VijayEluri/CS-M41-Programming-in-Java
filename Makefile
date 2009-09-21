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

ifeq ($(language),java)
programs = $(wildcard Chapter$(chapter)/Section$(section)/*.java)
programs := $(programs:.java=)
else
programs = $(wildcard Chapter$(chapter)/Section$(section)/*.cpp)
programs := $(programs:.cpp=)
endif
programs := $(notdir $(programs))
executables = $(addprefix $(bin_dir)/, $(programs))


all : $(executables)

ifeq ($(language),java)
$(executables) : $(bin_dir)/% : Chapter$(chapter)/Section$(section)/%.java | $(bin_dir)
	gcj Chapter$(chapter)/Section$(section)/$*.java --main=$* -o $(bin_dir)/$*
else
$(executables) : $(bin_dir)/% : Chapter$(chapter)/Section$(section)/%.cpp | $(bin_dir)
	g++ Chapter$(chapter)/Section$(section)/$*.cpp -o $(bin_dir)/$*
endif

$(directories) :
	mkdir -p $@

