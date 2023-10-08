MAINPROG=createPolyhedra
CPPS= createPolyhedra.cpp Polyhedron.cpp Sphere.cpp Point.cpp \
      PolyhedronFactory.cpp BoundingBox.cpp Cylinder.cpp
TEST_CPPS= Polyhedron.cpp Point.cpp PolyhedronFactory.cpp \
      BoundingBox.cpp Sphere.cpp Cylinder.cpp TestCylinder.cpp

DIR=${PWD}
ASST=$(notdir ${DIR})
ifneq (,$(findstring MinGW,$(PATH)))
DISTR=MinGW
EXE=.exe
LFLAGS=
else
DISTR=Unix
EXE=
LFLAGS=-fsanitize=leak,address -fuse-ld=gold
endif
#
########################################################################
# Macro definitions for "standard" C and C++ compilations
#
CPPFLAGS=-g -std=c++11 -D$(DISTR) -Wall -Wextra -Wpedantic -Weffc++
CFLAGS=-g
TARGET=$(MAINPROG)$(EXE)
TEST_TARGET=testCylinder$(EXE)
LINK=g++ $(CPPFLAGS)
#
CC=gcc
CPP=g++
#
#
#  In most cases, you should not change anything below this line.
#
#  The following is "boilerplate" to set up the standard compilation
#  commands:
#


OBJS=$(CPPS:%.cpp=%.o)
DEPENDENCIES = $(CPPS:%.cpp=%.d)

TEST_OBJS=$(TEST_CPPS:%.cpp=%.o)

%.d: %.cpp
	touch $@

%.o: %.cpp
	$(CPP) $(CPPFLAGS) -MMD -o $@ -c $*.cpp

#
# Targets:
#
all: $(TARGET) tests


win: $(OBJS)
	$(LINK) $(FLAGS) -o $(TARGET) $(OBJS)

$(TARGET): $(OBJS)
	$(LINK) $(FLAGS) -o $(TARGET) $(OBJS) $(LFLAGS)

tests: $(TEST_OBJS)
	$(LINK) $(FLAGS) -o $(TEST_TARGET) $(TEST_OBJS)


clean:
	-/bin/rm -f *.d *.o $(TARGET) $(TEST_TARGET)



make.dep: $(DEPENDENCIES)
	-cat $(DEPENDENCIES) > $@

include make.dep
