JAVAC=javac
PACKAGES=edu/cornell/mhk98/calculus \
         edu/cornell/mhk98/function \
         edu/cornell/mhk98/function/transcendentals \
         edu/cornell/mhk98/number \
         edu/cornell/mhk98/util
SRC_DIR=./src/
BIN_DIR=./bin/

JAVAFLAGS= -g -d $(BIN_DIR) -cp $(SRC_DIR)
COMPILE= $(JAVAC) $(JAVAFLAGS)
SPACE=
JAVA_FILES = $(subst $(SRC_DIR), $(SPACE), $(wildcard $(SRC_DIR)*.java))

ifdef PACKAGES
PACKAGEDIRS = $(addprefix $(SRC_DIR), $(PACKAGES))
PACKAGEFILES = $(subst $(SRC_DIR), $(SPACE), $(foreach DIR, $(PACKAGEDIRS), $(wildcard $(DIR)/*.java)))
ALLFILES = $(PACKAGEFILES) $(JAVA_FILES)
else
ALLFILES = $(JAVA_FILES)
endif

CLASS_FILES = $(ALLFILES:.java=.class)

all: $(addprefix $(BIN_DIR), $(CLASS_FILES))

$(BIN_DIR)%.class: $(SRC_DIR)%.java
	mkdir -vp bin
	$(COMPILE) $<

clean:
	rm -rf $(BIN_DIR)*
	rm -rf $(BIN_DIR)
