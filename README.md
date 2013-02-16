Chaikin
=======

##Overview##
A Java/Swing implementation of the Chaikin's algorithm using L-systems.
This project was done as a course project for the course in *Grammars
for generative art*, taught in the Faculty of Mathematics and Informatics
to Sofia University "St. Kliment Ohridski"

##Algorithm##
The algorithm is applied on curve with N vertexes. The goal is to get a smooth curve,
similar to a quadratic B-spline with control points at the vertexes of the initial
curve. 

The algorithm iteratively removes the edges of the curve, by replacing each
one of the vertexes with another two. These two points are located at the edges
that start from the respective vertex. Their coordinates are computed as follows:

<img src="http://www.idav.ucdavis.edu/education/CAGDNotes/Chaikins-Algorithm/img8.gif" alt="chaikin" />

<img src="http://www.idav.ucdavis.edu/education/CAGDNotes/Chaikins-Algorithm/img9.gif" /> <img src="http://www.idav.ucdavis.edu/education/CAGDNotes/Chaikins-Algorithm/img10.gif" /> <img src="http://www.idav.ucdavis.edu/education/CAGDNotes/Chaikins-Algorithm/img14.gif" />

<img src="http://www.idav.ucdavis.edu/education/CAGDNotes/Chaikins-Algorithm/img12.gif" /> <img src="http://www.idav.ucdavis.edu/education/CAGDNotes/Chaikins-Algorithm/img13.gif" /> <img src="http://www.idav.ucdavis.edu/education/CAGDNotes/Chaikins-Algorithm/img14.gif" />

##L-systems##
An L-system specifies a set of rules to be applied on an input string.
L-systems are very similar in their nature to context-free grammars, but with
the difference that an L-system applies all of its rules simultaneously.

Example L-system:

axiom: F->F+F-F <br />
input: F+F-F+F <br />

result of single application of the rules of the L-system over the input string: <br/>
F+F-F+F+F-F-F+F-F+F+F-F


##License##
The code can be freely used and distributed under the terms of the GPL license.
See the LICENSE file for details

##Author##
Martin Asenov Asenov
<br />
asenov.m@gmail.com
