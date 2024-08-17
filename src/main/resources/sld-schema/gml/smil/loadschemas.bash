#!/bin/bash

grep schemaLocation smil20.xsd | awk '{print $3}' | sed -e 's/schemaLocation="//' -e 's?"/>??' -e 's#[\n\r]##' > a.a

for file in `cat a.a`
do
  echo $file
  echo "curl https://www.w3.org/2001/SMIL20/$file -o $file"
  curl https://www.w3.org/2001/SMIL20/$file -o $file
done
