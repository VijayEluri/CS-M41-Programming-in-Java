# Oliver Kullmann, 4.10.2012 (Swansea)

remote_host="csoliver@cs-svr1.swansea.ac.uk"
git_dir="~/public_html/ProgrammingJava201516_KoU7Z9bzTn/CS-M41_Programs"

.PHONY : all public public-html

all : public

public : public-html

public-html :
	ssh $(remote_host) "cd $(git_dir); git status; git pull"

