/**
 * Created by Chen_Jun_Yu on 2017/11/15.
 */
$( document ).ready(function() {

    /*-- 下拉式選單滑鼠移入移出操作 --*/
    $('.dropdown').mouseenter(
        function() {
            $(this).addClass('show');
            $(this).children('.dropdown-toggle').attr('aria-expanded','true');
            $(this).children('.dropdown-menu').addClass('show');
        }
    );
    $('.dropdown').mouseleave(
        function() {
            $(this).removeClass('show');
            $(this).children('.dropdown-toggle').attr('aria-expanded','false');
            $(this).children('.dropdown-menu').removeClass('show');
        }
    );
    $('.dropdown-menu').mouseenter(
        function() {
            $(this).addClass('show');
            $(this).children('.dropdown-toggle').attr('aria-expanded','true');
            $(this).children('.dropdown-menu').addClass('show');
        }
    );
    $('.dropdown-menu').mouseleave(
        function() {
            $(this).removeClass('show');
            $(this).children('.dropdown-toggle').attr('aria-expanded','false');
            $(this).children('.dropdown-menu').removeClass('show');
        }
    );

    $('#shopCart').mouseenter(
        function(){
            $(this).children('img').attr('src','./images/shopCart-2.png');
        }
    );
    $('#shopCart').mouseleave(
        function(){
            $(this).children('img').attr('src','./images/shopCart-1.png');
        }
    );

    /*-- 更改商品分類的文字，滑鼠移上去從中文變英文 --*/
    $('.category_child1').mouseenter(
        function(){
            $(this).text('Digit');
        }
    );
    $('.category_child1').mouseleave(
        function(){
            $(this).text('3C數位');
        }
    );
    $('.category_child2').mouseenter(
        function(){
            $(this).text('Appliances');
        }
    );
    $('.category_child2').mouseleave(
        function(){
            $(this).text('家電');
        }
    );
    $('.category_child3').mouseenter(
        function(){
            $(this).text('Food');
        }
    );
    $('.category_child3').mouseleave(
        function(){
            $(this).text('食品');
        }
    );
    $('.category_child4').mouseenter(
        function(){
            $(this).text('Apparel');
        }
    );
    $('.category_child4').mouseleave(
        function(){
            $(this).text('服飾');
        }
    );
    $('.category_child5').mouseenter(
        function(){
            $(this).text('Makeups');
        }
    );
    $('.category_child5').mouseleave(
        function(){
            $(this).text('美妝');
        }
    );
    $('.category_child6').mouseenter(
        function(){
            $(this).text('Life');
        }
    );
    $('.category_child6').mouseleave(
        function(){
            $(this).text('生活');
        }
    );

});

