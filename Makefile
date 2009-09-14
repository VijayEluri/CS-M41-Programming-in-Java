bin_dir = ../bin/

HelloWorld : $(bin_dir)/HelloWorld
UseArgument : $(bin_dir)/UseArgument

$(bin_dir)/% : %.java
	gcj $*.java --main=$* -o $(bin_dir)/$*

