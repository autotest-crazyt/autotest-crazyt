;~  ��������ʾ

	Do
;~ 		
		If(WinExists("[CLASS:#32770]","ע��ɹ�")) Then
			
			
			WinSetOnTop("[CLASS:#32770]", "ע��ɹ�", 1)
			WinActivate( "[CLASS:#32770]","ע��ɹ�"  )
			;~MsgBox(0, "", "���±����ڴ���")
			$title = WinGetTitle("[CLASS:#32770]", "ע��ɹ�")

		EndIf
		
	Until WinActive("[CLASS:#32770]") 
;~ 	WinWaitActive("[CLASS:#32770]", "", 1)<>0 

	ControlClick("[CLASS:#32770]","","[CLASS:Button; INSTANCE:1]")

	
	
	
	
	
