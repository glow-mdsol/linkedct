#
# Copyright 2009-2015 Oktie Hassanzadeh
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

import urllib
import os
import time

#location = "c:\\ctdata\\" 
location = ""

inputfilename = location + "filelist2.txt"

infile = open(inputfilename,"r")

outfilename = location + "errors2.txt"


for line in infile:
    trial_url = "http://clinicaltrials.gov" + line.strip() + "?displayxml=true"
    trial_url = trial_url.replace('/ct2','')
    trialid = line.strip()[10:]
    uri = 'http://data.linkedct.org:8081/processxml/' + trial_url + '/'
    
    try:
        file = urllib.urlopen(uri)
    except:
        time.sleep(10)
        try:
            file = urllib.urlopen(uri)
        except:
            time.sleep(100)
            file = urllib.urlopen(uri)
    
    
    o = file.readlines()
    if o!=['OK']:
        #outfile.writelines(file.readlines())
        outfile = open(outfilename,"w")
        print >>outfile, trial_url
        print 'Error in URL: ' + trial_url
        outfile.close()
    else:
        print 'Done with trial ' + trialid


