/**
 * 
 */

function Jump() {
    location.href = "#jumpto";
}
 


function test(path){
	alert("テストメソッド");
	let form = document.getElementsByTagName('form')[0];
    if(!form){
        alert('フォームが取得できませんでした');
        return;
    }
    alert(path);
    form.action=path;
    form.method="get";
    form.submit();
}