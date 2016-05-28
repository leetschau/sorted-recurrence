import json
import sys

'''
Test if all recurrences are ordered:

    python ordertest.py <test-target>.json
'''

fairs = json.loads(open(sys.argv[1]).read())

for fair in fairs:
    recs = fair['recurrence']
    if any(recs[i]['timeStart']['$date'] < recs[i+1]['timeStart']['$date']
           for i in range(len(recs) - 1)):
        print('fair ' + fair['_id'] + ' is not sorted')
