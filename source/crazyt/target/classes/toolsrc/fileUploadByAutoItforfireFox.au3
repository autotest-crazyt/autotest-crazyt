

	Do
;~ 		MsgBox(0, "", "��λ�ļ��ϴ��Ի���")
		If(WinExists("[CLASS:#32770]","�ļ���")) Then
			WinActivate( "[CLASS:#32770]","�ļ���"  )
		

		EndIf
		
	Until WinWaitActive("[CLASS:#32770]","�ļ���", 1)<>0 

	ControlClick("","",1001,"left","1","350","11")

	
		Sleep(1000)
;~ 		MsgBox(0, "", $CmdLine[1])
	ControlSetText("","","",$CmdLine[1])

	Sleep(1000)
	Send("{ENTER}")
	ControlSetText("","",1148,$CmdLine[2])
	Sleep(1000)
	ControlClick("","",1)
	
	
	
	
