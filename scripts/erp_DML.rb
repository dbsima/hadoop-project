put 'employees', '1', 'identification:firstname', 'Dragos'
put 'employees', '1', 'identification:lastname', 'SIMA'
put 'employees', '1', 'department:name', 'FR'

put 'employees', '2', 'identification:firstname', 'George'
put 'employees', '2', 'identification:lastname', 'BACOVIA'
put 'employees', '2', 'department:name', 'FR'

put 'employees', '3', 'identification:firstname', 'Mihai'
put 'employees', '3', 'identification:lastname', 'EMINESCU'
put 'employees', '3', 'department:name', 'HR'


put 'projects', '1', 'identification:name', 'Amazon'
put 'projects', '1', 'details:description', '-'

put 'projects', '2', 'identification:name', 'Google'
put 'projects', '2', 'details:description', '-'


put 'defects', '1', 'identification:name', 'Bug1'
put 'defects', '1', 'details:severity', 'Level 5'
put 'defects', '1', 'details:description', '-'
put 'defects', '1', 'reference:projectID', '1'
put 'defects', '1', 'reference:employeeID', '1'

put 'defects', '2', 'identification:name', 'Bug2'
put 'defects', '2', 'details:severity', 'Level 3'
put 'defects', '2', 'details:description', '-'
put 'defects', '2', 'reference:projectID', '1'
put 'defects', '2', 'reference:employeeID', '1'

put 'defects', '3', 'identification:name', 'Bug1'
put 'defects', '3', 'details:severity', 'Level 1'
put 'defects', '3', 'details:description', '-'
put 'defects', '3', 'reference:projectID', '2'
put 'defects', '3', 'reference:employeeID', '1'

put 'defects', '4', 'identification:name', 'Bug2'
put 'defects', '4', 'details:severity', 'Level 1'
put 'defects', '4', 'details:description', '-'
put 'defects', '4', 'reference:projectID', '2'
put 'defects', '4', 'reference:employeeID', '1'

put 'defects', '5', 'identification:name', 'Bug1'
put 'defects', '5', 'details:severity', 'Level 1'
put 'defects', '5', 'details:description', '-'
put 'defects', '5', 'reference:projectID', '2'
put 'defects', '5', 'reference:employeeID', '3'

put 'contributions', '1', 'reference:defectID', '1'
put 'contributions', '1', 'reference:employeeID', '1'
put 'contributions', '1', 'details:comment', 'I will fix it.'
put 'contributions', '1', 'details:date', '2014-01-01'

put 'contributions', '2', 'reference:defectID', '2'
put 'contributions', '2', 'reference:employeeID', '2'
put 'contributions', '2', 'details:comment', 'I will fix it.'
put 'contributions', '2', 'details:date', '2014-01-01'

put 'contributions', '3', 'reference:defectID', '3'
put 'contributions', '3', 'reference:employeeID', '1'
put 'contributions', '3', 'details:comment', 'I will fix it.'
put 'contributions', '3', 'details:date', '2014-01-01'

put 'contributions', '4', 'reference:defectID', '4'
put 'contributions', '4', 'reference:employeeID', '1'
put 'contributions', '4', 'details:comment', 'I will fix it.'
put 'contributions', '4', 'details:date', '2014-01-01'

put 'contributions', '5', 'reference:defectID', '2'
put 'contributions', '5', 'reference:employeeID', '1'
put 'contributions', '5', 'details:comment', 'I will fix it.'
put 'contributions', '5', 'details:date', '2014-01-01'

put 'contributions', '6', 'reference:defectID', '5'
put 'contributions', '6', 'reference:employeeID', '1'
put 'contributions', '6', 'details:comment', 'I will fix it.'
put 'contributions', '6', 'details:date', '2014-01-01'

put 'contributions', '7', 'reference:defectID', '5'
put 'contributions', '7', 'reference:employeeID', '2'
put 'contributions', '7', 'details:comment', 'I will fix it.'
put 'contributions', '7', 'details:date', '2014-01-01'

put 'contributions', '8', 'reference:defectID', '5'
put 'contributions', '8', 'reference:employeeID', '3'
put 'contributions', '8', 'details:comment', 'I will fix it.'
put 'contributions', '8', 'details:date', '2014-01-01'







