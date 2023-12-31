// 정규표현식
//
// ^ab     | 줄 시작에 있는 ab와 일치
// ab$     | 줄 끝에 있느 ab와 일치
// .        | 임의의 한 문자와 일치
// a|b      | a 또는 b와 일치
// ab?      | b가 없거나 b와 일치
//
// {3}      | 3개 연속 일치
// {3,}     | 3개 이상 연속 일치
// { 3,5}   | 3개 이상 5개 이하(3~5개) 연속 일치
//  +       | 1개 이상 연속 일치
//
// [ab]     | a 또는 b
// [a-z]    | a부터 z 사이의 문자 구간에 일치 (영어 소문자)
// [A-Z]    | A부터 Z 사이의 문자 구간에 일치 (영어 대문자)
// [0-9]    | 0부터 9 사이의 문자 구간에 일치 (숫자)
// [가-힣] | 가부터 힣 사이의 문자 구간에 일치 (한글)
//
// \w  | 63개 문자 (word, 대소영문52개 + 숫자 10개 + _)에 일치
// \b   | 63개 문자에 일치하지 않는 문자 경계 (boundary)
// \d   | 숫자에 일치
// \s   | 공백(space, tab 등에 일치)
//
// \\ (?:)  | 그룹 지정
// \\ (?=)  | 앞쪽 일치 (lookahead)
// \\ (?<=) | 뒤쪽 일치 (lookbehind)
//
//
//
