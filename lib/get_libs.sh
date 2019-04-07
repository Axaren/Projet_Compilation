#!/bin/bash

JFLEX_ARCHIVE="jflex-1.7.0.tar.gz"
JFLEX_DIR="jflex-1.7.0"
BEAVER_ARCHIVE="beaver-0.9.11.zip"
BEAVER_DIR="beaver-0.9.11"

echo -e "https://www.jflex.de/release/$JFLEX_ARCHIVE\nhttps://freefr.dl.sourceforge.net/project/beaver/$BEAVER_ARCHIVE" > libs.txt
wget -i libs.txt && rm libs.txt
tar -xzf $JFLEX_ARCHIVE && rm $JFLEX_ARCHIVE
unzip $BEAVER_ARCHIVE && rm $BEAVER_ARCHIVE
mv -v "$JFLEX_DIR/lib/jflex-full-1.7.0.jar" .
rm -r $JFLEX_DIR
mv -v $BEAVER_DIR/lib/*.jar .
rm -r $BEAVER_DIR


