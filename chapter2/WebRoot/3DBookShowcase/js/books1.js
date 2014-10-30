var Books = (function() {

	var $books = $( '#bk-list > li > div.bk-book' ), booksCount = $books.length;
	
	function init() {

		$books.each( function() {
			
			var $book = $( this ),
				$other = $books.not( $book ),
				$parent = $book.parent(),
				$page = $book.children( 'div.bk-page' ),
				$bookview = $parent.find( 'button.bk-bookview' ),
				$content = $page.children( 'div.bk-content' ), current = 0;

			$parent.find( 'button.bk-bookback' ).on( 'click', function() {				
				
				$bookview.removeClass( 'bk-active' );

				if( $book.data( 'flip' ) ) {
					
					$book.data( { opened : false, flip : false } ).removeClass( 'bk-viewback' ).addClass( 'bk-bookdefault' );

				}
				else {
					
					$book.data( { opened : false, flip : true } ).removeClass( 'bk-viewinside bk-bookdefault' ).addClass( 'bk-viewback' );

				}
					
			} );

			$bookview.on( 'click', function() {

				var $this = $( this );			
				
				$other.data( 'opened', false ).removeClass( 'bk-viewinside' ).parent().css( 'z-index', 0 ).find( 'button.bk-bookview' ).removeClass( 'bk-active' );
				if( !$other.hasClass( 'bk-viewback' ) ) {
					$other.addClass( 'bk-bookdefault' );
				}

				if( $book.data( 'opened' ) ) {
					$this.removeClass( 'bk-active' );
					$book.data( { opened : false, flip : false } ).removeClass( 'bk-viewinside' ).addClass( 'bk-bookdefault' );
					/*
					 * 
					$other.css("display","");//
					$book.css("zoom","400%");//
					$book.css("width",$book.width()/4);//
					$book.css("height",$book.height()/4);//
				
					  var $front=$book.find(".bk-front"),
					    $page=$book.find(".bk-page"),
					    $right=$book.find(".bk-right"),
					    $left=$book.find(".bk-left"),
					    $top=$book.find(".bk-top"),
					    $bottom=$book.find(".bk-bottom"),
					    $cover=$book.find(".bk-cover"),
					    $back=$book.find(".bk-cover-back"); 
					$front.css("height",$front.height()/4);//
					$page.css("height",$page.height()/4);//
					$right.css("height",$right.height()/4);//
					$left.css("height",$left.height()/4);//
					$cover.css("height",$cover.height()/4);//
					$back.css("height",$back.height()/4);//
					
					$front.css("width",$front.width()/3);//
					$page.css("width",$page.width()/3);//
					$left.css("width",$left.width()/3);//
					$cover.css("width",$cover.width()/3);//
					$back.css("width",$back.width()/3);//
					$right.css("width",$right.width()/3);//
					$top.css("width",$top.width()/3);//
					$bottom.css("width",$bottom.width()/3);//
					
					var $search=$('#db-nav-book');
					$search.css("display","");//
					$this.html("View inside");
					*/
					
				}
				else {
					$this.addClass( 'bk-active' );
					$book.data( { opened : true, flip : false } ).removeClass( 'bk-viewback bk-bookdefault' ).addClass( 'bk-viewinside' );
					$parent.css( 'z-index', booksCount );
					current = 0;
					$content.removeClass( 'bk-content-current' ).eq( current ).addClass( 'bk-content-current' );
					/*
					 * 
					$other.css("display","none");//
					//$book.css("zoom","400%");//
					$book.css("width",$book.width()*4);//
					$book.css("height",$book.height()*4);//
					var $front=$book.find(".bk-front"),
					    $page=$book.find(".bk-page"),
					    $right=$book.find(".bk-right"),
					    $left=$book.find(".bk-left"),
					    $top=$book.find(".bk-top"),
					    $bottom=$book.find(".bk-bottom"),
					    $cover=$book.find(".bk-cover"),
					    $back=$book.find(".bk-cover-back");
					
					$front.css("height",$front.height()*4);//
					$page.css("height",$page.height()*4);//
					$right.css("height",$right.height()*4);//
					$left.css("height",$left.height()*4);//
					$cover.css("height",$cover.height()*4);//
					$back.css("height",$back.height()*4);//
					
					$front.css("width",$front.width()*3);//
					$page.css("width",$page.width()*3);//
					//$left.css("width",$left.width()*3);//
					$cover.css("width",$cover.width()*3);//
					$back.css("width",$back.width()*3);//
					$right.css("width",$right.width()*3);//
					$top.css("width",$top.width()*3);//
					$bottom.css("width",$bottom.width()*3);//
					var $search=$('#db-nav-book');
					$search.css("display","none");//
					$this.html("Go back");	
					*/
					
				}

			} );

			if( $content.length > 1 ) {

				var $navPrev = $( '<span class="bk-page-prev">&lt;</span>' ),
					$navNext = $( '<span class="bk-page-next">&gt;</span>' );
				
				$page.append( $( '<nav></nav>' ).append( $navPrev, $navNext ) );

				$navPrev.on( 'click', function() {
					if( current > 0 ) {
						--current;
						$content.removeClass( 'bk-content-current' ).eq( current ).addClass( 'bk-content-current' );
					}
					return false;
				} );

				$navNext.on( 'click', function() {
					if( current < $content.length - 1 ) {
						++current;
						$content.removeClass( 'bk-content-current' ).eq( current ).addClass( 'bk-content-current' );
					}
					return false;
				} );

			}
			
		} );

	}

	return { init : init };

})();