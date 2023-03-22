clean:
	rm -rf project
	rm -rf target

build:
	sbt

run:
	sbt run

run_toy:
	./example_toy.sh