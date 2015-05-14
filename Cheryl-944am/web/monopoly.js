/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * AUTHOR: Gurpartap Gill
 */

/*global variables and can be accessed anywhere in the script*/
                  var spaceId = 0;
                  var isInJail = "No";
                  var isProperty = true;
                  /*length & height of token*/
                  var length = 40;
                  var height = 30;
                  var prevCenterX = 646; // holds previous X of token
                  var prevCenterY = 646; // holds prevoius y of token
                  var centerX = 646; // holds current token position x
                  var centerY = 646; // holds current token position y
                  var canvas = document.getElementById("monopolyCanvas");
                  var context = canvas.getContext("2d");
                  /*var token = new Image();
                  token.src = 'images/blue_token.png';
                  token.width = '40';
                  token.height = '30';
                  var pat = context.createPattern(token, 'no-repeat');*/
                  context.fillRect(prevCenterX, prevCenterY, length, height);
                  //context.fillStyle = pat;
                  //context.fill();
                  //context.fillect(prevCenterX, prevCenterY, length, height);
                  
                  //context.fill();
                  
               /* function setTokenContext(){
                
                  var token = new Image();
                  token.src = 'images/blue_token.png';
                  var pat = context.createPattern(token, 'no-repeat');
                  context.fillStyle = pat;
                  context.fill();
                }*/  
                function movePlayer(diceTotal){
                    
                    /*starting from GO, we update the spaceId per diceValue*/
                    spaceId = spaceId + diceTotal;
                    var i = 0;
                    //document.getElementById("h").innerHTML = "Dice rolled2: "+diceTotal;
                        
                    for(; i < diceTotal; i++){
                    /*starting from go, and moving left on board*/
                      if((centerX === 646 && centerY === 646) || centerY === 646){
                          /*when moving from Corner-Space, decrease x by 74*/

                          /*change x for the max of 10 diceValue, and the rest goes */
                          //for(; i < diceTotal; i++){
                              /*moving from Go corner to next space. moving from space to GoJail corner */
                              if((centerX === 646 && centerY === 646) || centerX === 124)
                                  centerX = xSpaceCornerLeft();
                              /*moving from GoJail corner to space*/
                              else if(centerX === 50 && centerY ===646)
                                  centerY = ySpaceCornerUp();
                              else if (centerX > 50)
                                  centerX = centerX - 56;
                              else 
                                  centerY = centerY - 56;

                          /*if the token hit the bottom-left corner, then change Y to go up also*/

                      }
                      /*if the token is the bottom-left corner or left vertical row. Then moves up*/
                      else if((centerX === 50 && centerY === 646) || centerX === 50){

                          //for(; i < diceTotal; i++){
                              /*moving from Jail corner to next space. moving from space to Free Parking corner*/
                              if((centerX === 50 && centerY === 646) || centerY === 124 )
                                  centerY = ySpaceCornerUp();

                              /*moving from Free Parking corner to next space*/
                              else if(centerX === 50 && centerY ===50)
                                  centerX = xSpaceCornerRight();
                              else if(centerY > 50)
                                  centerY = centerY - 56;
                              else 
                                  centerX = centerX + 56;

                             //}
                          /*now if the token reaches top-left corner, then change X to go right also*/

                      }
                      /*if the token is at the top-left corner or the top horizontal row. Then move towards right*/
                      else if((centerX === 50 && centerY === 50) || centerY === 50){
                          //for(; i < diceTotal; i++){
                              /*moving from FreeParking to next space. moving from space to GoJail corner*/
                              if((centerX === 50 && centerY === 50) || centerX === 572)
                                  centerX = xSpaceCornerRight();

                              else if(centerX < 646)
                                  centerX = centerX + 56;
                              /*moving from GoJail corner to next space*/
                              else if(centerX === 646 && centerY ===50)
                                  centerY = ySpaceCornerDown();
                              else
                                  centerY = centerY + 56;
                             //}
                          /*now if the token reaches at top-right corner, change Y to go down.*/

                      }
                      /*if the token is at the top-right corner or right vertical row. Then move the token down*/
                      else if((centerX === 646 && centerY === 50) || centerX === 646){
                          //for(; i< diceTotal; i++){
                              /*move from GoJail corner to space. moving from space to Go Corner*/
                              if((centerX === 646 && centerY === 50) || centerY === 572)
                                  centerY = ySpaceCornerDown();
                              /*move from Go corner to next space*/
                              else if(centerX === 646 && centerY ===646)
                                  centerX = xSpaceCornerLeft();
                              else if(centerY < 646)
                                  centerY = centerY + 56;
                             
                             else
                                 centerX = centerX - 56;
                          /*now if Token reaches bottom-left corner, then change X to go left*/

                      }
                  }
                      
                      //token.onload = function() {
                      context.clearRect(prevCenterX, prevCenterY, length, height);
                      context.fillRect(centerX-10, centerY, length, height);
                      //context.rect(centerX-10, centerY, length, height);
                      
                      saveOldXY(centerX-10, centerY);
                      
                      //actionSpace(spaceId);
                        //};
                      /*context.beginPath();
                      context.arc(centerX, centerY, 10, 0, 2 * Math.PI, false);
                      context.fillStyle = 'black';
                      context.fill();
                      context.lineWidth = 5; */
                      
            }
                /*saves the prev x,y of the token to erase token position on the board and update using the new x,y*/
                function saveOldXY(x, y){
                    prevCenterX = x;
                    prevCenterY = y;
                }
                /*changing x, y co-ordinates as token moves between Space-Corner or vice versa*/
                function xSpaceCornerLeft(){
                    return centerX - 74;
                }
                function ySpaceCornerUp(){
                    return centerY - 74;
                }
                function xSpaceCornerRight(){
                    return centerX + 74;
                }
                function ySpaceCornerDown(){
                    return centerY + 74;
                }
                function getIsInJail(){
                    return isInJail;
                }
                function setIsInJail(value){
                    isInJail = value;
                }
                function updateSpaceId(){
                    /*if the player passed GO, reset the spaceId so that it corresponds to 0 if at Go Corner*/
                    if(spaceId > 39){
                        spaceId = spaceId - 40;
                        /*should also assign PassGO credit here*/
                    }
                    
                }
               
                
                function setIsProperty(value){
                    isProperty = value;
                }
                function getIsProperty(){
                    return isProperty;
                }
                function setSpaceId(id){
                    spaceId = id;
                }
                function returnSpaceId(){
                    return spaceId;
                }
                function moveToJail(){
                    /*change x, y to Jail space*/
                    centerX = 50; centerY = 646;
                      /*context.clearRect(prevCenterX, prevCenterY, length, height);
                      context.fillRect(centerX-10, centerY, length, height);
                      saveOldXY(centerX-10, centerY);
                      spaceId = 10;   */
                    context.clearRect(prevCenterX, prevCenterY, length, height);
                    context.fillRect(centerX-10, centerY, length, height);
                      //context.rect(centerX-10, centerY, length, height);
                      
                      saveOldXY(centerX-10, centerY);
                      
                }
