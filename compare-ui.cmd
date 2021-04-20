:: Beginning of COMPARE batch file
:: This file is created for setting command line parameters.
:: All of this parameters are MANDATORY.
::
:: STORAGE - path to folder where API dumps of 1st endpoint are stored
:: (Example: STORAGE_1=S3:/spark-etl-us/check/dumps/20180608/US-C8   OR  STORAGE=C:\Users\Docs\Test-Results\US-C8\)
set STORAGE_1=%1
::
:: STORAGE - path to folder where API dumps of 2nd endpoint are stored
:: (Example: STORAGE_1=S3:/spark-etl-us/check/dumps/20180608/US-C10  OR  STORAGE=C:\Users\Docs\Test-Results\US-C10\)
set STORAGE_2=%2
::
:: STORAGE - path to folder where test reports will be saved
:: (Example: STORAGE_1=S3:/spark-etl-us/check/dumps/20180608/US-C8-C10-COMPARISON-RESULTS  OR  STORAGE=C:\Users\Docs\Test-Results\US-C8-C10-COMPARISON-RESULTS\)
set STORAGE_3=%3
::
:: Execution commands. PLEASE, DON'T EDIT IT!
Setlocal EnableDelayedExpansion
SET STORAGE_1=!STORAGE_1:\=/%!
SET STORAGE_2=!STORAGE_2:\=/%!
SET STORAGE_3=!STORAGE_3:\=/%!
java -jar pinpoint-spark-api-comparisons.jar compare-summaries -s1 %STORAGE_1% -s2 %STORAGE_2% -sr %STORAGE_3% 
::
:: End of COMPARE file
pause
exit