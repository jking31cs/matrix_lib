# Simple Graphics Pipeline Demo

This is for CS8803-GPU Programming in Video Games.  The purpose of this assignment was to demonstrate our understanding
of a simple graphics pipeline, rendering a list of triangles and doing lighting calculations.

To run this code, in command line, run {code} ./gradlew run {code} and it should download gradle 2.4 to you machine then
run the code.  It'll ask you to select a file, which you can select any raw triangle file to render.  I've tested this
with both shark.raw and mewtwo.raw and had successful results.

You can use the WASD keys to move the camera around, which I do by rotating it around the X/Y axis so that it's always
looking at the same point.  This shows that my code understands and does the computations for view transformations
and projection transformations correctly.

As for lighting, in its current state, the colors are hard coded since the raw triangle format doesn't include colors.
The material colors are red with a little bit of ambient lighting, and I've set the specular constant to 5.  You can
see that the calculations are running, and some bits of the polygons are shiny.
