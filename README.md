# Lifted Functional Equivalence
CLEVER V2

## Introduction

TODO insert content

## Getting Started
### Prerequisites:
1. Clone the repository
```
git clone https://github.com/stepwise-alan/spl-tools.git
cd spl-tools
```

2. Install Python 3.10

3. Install the Python requirements
```
pip3 install -r requirements.txt
```


4. Install [Java](https://ubuntu.com/tutorials/install-jre#2-installing-openjdk-jre)

5. Install Java Version 1.8
```
sudo apt install openjdk-8-jdk
```

6. Switch to Java version 1.8.
```
# On Ubuntu 
update-java-alternatives --list
sudo update-java-alternatives --set /usr/lib/jvm/java-1.8.0-openjdk-amd64 # or whatever the path is from the previous command

java -version
# Should return 
# openjdk version "1.8.0_362"
```

7. Install [SBT](https://www.scala-sbt.org/download.html)

8. Install the latest fork of TypeChef
```
cd ~ # or any other parent directory
git clone https://github.com/stepwise-alan/TypeChef
# Follow the instruction in this repository to build from source

# But this instruction should be something like
cd TypeChef
sbt publishLocal
```

9. Install [Z3](https://github.com/Z3Prover/z3)
```
sudo apt install z3
```

10. Install [Seahorn](https://github.com/seahorn/seahorn/)

Installed clang-14
installed ninja through repository and
sudo apt install ninja-build
installed lld
sudo apt install libstdc++-12-dev
sudo apt-get install libgmp3-dev

cmake .. -GNinja \
                                                -DCMAKE_BUILD_TYPE=RelWithDebInfo \
                                                -DZ3_ROOT=/opt/z3-4.8.9 \
                                                -DYICES2_HOME=/opt/yices-2.6.1 \
                                                -DCMAKE_INSTALL_PREFIX=run \
                                                -DCMAKE_CXX_COMPILER=clang++-14 \
                                                -DCMAKE_C_COMPILER=clang-14 \
                                                -DSEA_ENABLE_LLD=ON \
                                                -DCPACK_GENERATOR="TGZ" \
                                                -DCMAKE_EXPORT_COMPILE_COMMANDS=ON && \
                                                cmake --build . --target extra  && cmake .. && \
                                                cmake --build . --target crab  && cmake .. && \
                                                cmake --build . --target install && \
                                                cmake --build . --target units_z3 && \
                                                cmake --build . --target units_yices2 && \
                                                cmake --build . --target test_type_checker && \
                                                cmake --build . --target test_hex_dump && \
                                                cmake --build . --target package


Note that Seahorn requires Z3.
Be aware of the version compatibility of Z3 and Seahorn. 

This may be any version, but we have used version 
[dev14](https://github.com/seahorn/seahorn/tree/dev14), 
and we recommend you to do the same. 

Refer to the installation guide for the exact steps. 
Here are some useful tips.
```
# The easier way to install seahorn is with docker
docker pull seahorn/seahorn-llvm10:nightly
docker run --rm -it seahorn/seahorn-llvm10:nightly
```

9. Install [TypeChef-BusyboxAnalysis](https://github.com/stepwise-alan/TypeChef-BusyboxAnalysis) 

```
cd ~
git clone https://github.com/stepwise-alan/TypeChef-BusyboxAnalysis
```

The Java version needs to be 1.8
In Arch based Linux it can be set by using the ``archlinux-java`` command. Here is the [documentation](https://wiki.archlinux.org/title/Java).
If the Java version was switched, you may need to publish TypeChef again. 
```
# remove any cached publishes
rm -rf ~/.ivy2/local/de.fosd.typechef
cd ~/TypeChef
sbt publishLocal
```

Once, TypeChef is published with the right version of Java, follow these steps for the TypeChef-BusyboxAnalysis
```
cd ~/TypeChef-BusyboxAnalysis
sbt mkrun # under Java version 1.8
```


10. Install the Scala Requirements in this repository
   ```
   make build

   # alternatively you can run
   sbt # in the same directory as build.sbt
   ```

### To Run Complex Input

Build the BusyBox analyser

Use the instructions for the Busybox analysis
You can just clone the model, and it should come with the model. 
If you would like to find other examples, follow the instructions and use this script to evaluate the examples ``BusyBoxAnalyzer.scala``

<!-- 11. Build -->

## Usage

### Timeouts
In early usage, giving the wrong file, can clog your system into a non-halting situation. 
It is a good practice to prefix the commands with a ``timeout 2m``

### Arguments

TODO fill

#### Module

TODO fill

### Ways of Running

```shell
sbt "runMain EquivalenceCheckerV1 OLD_FILE NEW_FILE FUNCTION_NAME"
```
**Where `OLD_FILE`/`NEW_FILE` is the path to the old/new file, and `FUNCTION_NAME` is the function name.**

You can also simply run
```
sbt run
# or equivalently
make run
```

There is a toy shell that runs the toy example. 
You can invoke it by running
```
make run_toy
```

Or by running the file itself
```
./example_toy.sh
```

## Examples
```shell
sbt "runMain LiftedEquivalenceCheckerV1 examples/toy/old.c examples/toy/new.c client"
```
