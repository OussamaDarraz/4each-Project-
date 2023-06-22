#!/bin/bash
cd ../../database
psql -h 172.31.254.55 -U postgres -f db.sql -d refarmdb -x -q
