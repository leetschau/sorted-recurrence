import json

with open('../resources/fairs.json') as inp:
    fairs = []
    for line in inp:
        fair = json.loads(line.strip())
        recs = fair['recurrence']
        fair['recurrence'] = sorted(recs,
                                    key=lambda rec: rec['timeStart']['$date'],
                                    reverse=True)
        fairs.append(fair)
    with open('resources/fairsSorted.json', 'w') as out:
        out.write(json.dumps(fairs, ensure_ascii=False))
