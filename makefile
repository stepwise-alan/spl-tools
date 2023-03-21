clean:
	rm -rf project
	rm -rf target

build:
	sbt

run:
	./example_toy.sh