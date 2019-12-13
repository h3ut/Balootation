package com.h3uu.balootation;

import com.h3uu.balootation.card.Card;

/**
 *
 * @author RedDragon
 */
public class ObjectClicked {
    
    // what card was selected
    private Card whatCard;
    

    // card belong to the floor cards
    private boolean playedByMe, playedByFriend, playedByThemFirst, playedByThemSecond;
    // card is in hand of who?
    private boolean handMe, handFriend, handThemFirst, handThemSecond;
    private boolean isSun;
    private boolean isHokm;
    
    public ObjectClicked(){
        whatCard = null;
        isSun = false;
        isHokm = false;
    }

    public void setWhatCard(Card whatCard) {
        this.whatCard = whatCard;
    }

    public void setIsSun(boolean isSun) {
        this.isSun = isSun;
    }

    public void setIsHokm(boolean isHokm) {
        this.isHokm = isHokm;
    }

    public Card getWhatCard() {
        return whatCard;
    }

    public boolean isIsSun() {
        return isSun;
    }

    public boolean isIsHokm() {
        return isHokm;
    }

    public boolean isPlayedByMe() {
        return playedByMe;
    }

    public void setPlayedByMe(boolean playedByMe) {
        this.playedByMe = playedByMe;
    }

    public boolean isPlayedByFriend() {
        return playedByFriend;
    }

    public void setPlayedByFriend(boolean playedByFriend) {
        this.playedByFriend = playedByFriend;
    }

    public boolean isPlayedByThemFirst() {
        return playedByThemFirst;
    }

    public void setPlayedByThemFirst(boolean playedByThemFirst) {
        this.playedByThemFirst = playedByThemFirst;
    }

    public boolean isPlayedByThemSecond() {
        return playedByThemSecond;
    }

    public void setPlayedByThemSecond(boolean playedByThemSecond) {
        this.playedByThemSecond = playedByThemSecond;
    }

    public boolean isHandMe() {
        return handMe;
    }

    public void setHandMe(boolean handMe) {
        this.handMe = handMe;
    }

    public boolean isHandFriend() {
        return handFriend;
    }

    public void setHandFriend(boolean handFriend) {
        this.handFriend = handFriend;
    }

    public boolean isHandThemFirst() {
        return handThemFirst;
    }

    public void setHandThemFirst(boolean handThemFirst) {
        this.handThemFirst = handThemFirst;
    }

    public boolean isHandThemSecond() {
        return handThemSecond;
    }

    public void setHandThemSecond(boolean handThemSecond) {
        this.handThemSecond = handThemSecond;
    }

    @Override
    public String toString() {
        if(isHokm){
            return "7km was selected";
        }else if(isSun){
            return "sun was selected";
        }else if(whatCard != null){
            String s = whatCard.toString() + " was selected from ";
            if(handMe){
                s += "me hands";
            }else if(handFriend){
                s += "friend hands";
            }else if(handThemFirst){
                s += "them first hands";
            }else if(handThemSecond){
                s += "them second hands";
            }else if(playedByMe){
                s += "me played";
            }else if(playedByFriend){
                s += "friend played";
            }else if(playedByThemFirst){
                s += "them first played";
            }else if(playedByThemSecond){
                s += "them second played";
            }
            return s;
        }
        return "nothing was selected";
    }

    
    
    
}
