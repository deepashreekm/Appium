#cs 
Script unsets windows maximization
#ce

$winList = WinList ()

For $i = 1 to $winList[0][0]
  If BitAnd (WinGetState ($winList[$i][1]), 32) And $winList[$i][0] <> "" Then WinSetState ($winList[$i][1], "",@SW_RESTORE)
Next
  
WinMinimizeAll()