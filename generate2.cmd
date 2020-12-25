:: Beginning of GENERATE batch file
:: This file is created for set up command line parameters
:: Mandatory parameters - ENDPOINT_URL and STORAGE
:: 
:: ENDPOINT_URL - URL of testing endpoint.
:: (Example: ENDPOINT_URL=https://nmi-spark-g8.pointlogic.com)
set ENDPOINT_URL=https://nz-prod.k8s-ap-southeast-1-production.pointlogic.co
::
:: STORAGE - path to save generated API dumps
:: It can be local folder or folder on S3
:: If there are white spaces in path then surround it in quotation marks -> "C:\Users\Docs\Test Results\US C8\"
:: (Example: STORAGE=S3:/spark-etl-us/check/dumps/20180608/G8  OR  STORAGE=C:\Users\Docs\Test-Results\US-C8\ )
set STORAGE=C:\Users\nrybchak\IdeaProjects\pinpoint-spark-api-comparisons\NZ\NZ_PROD\
::
:: OPTIONS - path to Test Options file - Optional parameter.
:: If this parameter will be indicated, autotest will run with parameters from this properties file, 
:: in other way it will run with parameters from '/Config Files/testOptions.properties' file. 
:: (Example: OPTIONS=C:\Users\Docs\Test-Results\US-C8\testOptions.properties)
set OPTIONS=
::
:: Execution commands. PLEASE, DON'T EDIT IT!
Setlocal EnableDelayedExpansion
SET STORAGE=!STORAGE:\=/%!
IF [%OPTIONS%] NEQ [] (
java -jar pinpoint-spark-api-comparisons.jar generate-summary -url %ENDPOINT_URL% -s %STORAGE% -o %OPTIONS% 
)
IF [%OPTIONS%] EQU [] (
java -jar pinpoint-spark-api-comparisons.jar generate-summary -url %ENDPOINT_URL% -s %STORAGE% 
)
::
:: End of GENERATE batch file
pause