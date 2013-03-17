@echo off
rem ----------------------------------------------------------------
rem Copyright (c) 2006-2013, Cloudsmith Inc.
rem The code, documentation and other materials contained herein have been
rem licensed under the Eclipse Public License - v 1.0 by the copyright holder
rem listed above, as the Initial Contributor under such license. The text of
rem such license is available at www.eclipse.org.
rem ----------------------------------------------------------------

rem ----------------------------------------------------------------
rem This script will launch the Equinox framework
rem
rem usage:
rem   <script name> [ -vm <java binary> ] <command> <command opts> [ -vmargs <vmargs> ]
rem
rem Alternatively, you bypass this script and instead use:
rem   <java binary> <vmargs> -jar <equinox launcher jar> <command> <command opts>
rem ----------------------------------------------------------------

if [%OS%] == [WINNT] goto winnt
if [%OS%] == [Windows_NT] goto winnt
set EXE_DIR=.\
goto parseargs

:winnt
setlocal
set EXE_DIR=%~dp0

:parseargs
rem ----------------------------------------------------------------
rem Split the arguments into vm, vmargs and common args
rem ----------------------------------------------------------------
set VM=java
set ARGS=
set VMARGS=
:loop
	if [%1] == [] goto endloop
	if [%1] == [-vm] goto vm
	if [%1] == [-vmargs] goto vmargs
	set ARGS=%ARGS% %1
	shift
	goto loop
:vm
	shift
	set VM=%1
	shift
	goto loop

:vmargs
	shift
:vmargs_loop
	if [%1] == [] goto endloop
	set VMARGS=%VMARGS% %1
	shift
	goto vmargs_loop

:endloop

rem ----------------------------------------------------------------
rem Get path to equinox launcher jar and invoke
rem ----------------------------------------------------------------
for /f "delims= tokens=1" %%c in ('dir /B /S /OD "%EXE_DIR%"\plugins\org.eclipse.equinox.launcher_*.jar') do set EQUINOXJAR=%%c

%VM% %VMARGS% -jar "%EQUINOXJAR%" %ARGS%
