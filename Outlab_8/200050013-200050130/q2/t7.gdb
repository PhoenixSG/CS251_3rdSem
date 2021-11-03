break load_var
run 200050013-200050130
finish
set $retval = load_var()
print (char* ) $retval
quit
