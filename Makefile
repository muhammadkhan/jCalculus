JAVAC=javac
sources = $(wildcard *.java)
classes = $(sourcces:.java=.class)

all: $(classes)

clean:
	rm -f *.class

%.class : %.java
	$(JAVAC) $<
