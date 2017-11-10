package com.yue.pushload.pushload;

public enum RefreshState {
    None,//无状态

    PullDownToRefresh,//下拉到刷新状态的过程 箭头向下
    ReleaseToRefresh,//释放到刷新的状态 箭头向上
    RefreshReleased,//刷新释放 显示progressbar

    PullDownCanceled,//下拉取消状态
    Refreshing,//刷新中
    RefreshFinish,//刷新完成

    PullToUpLoad,//上拉到加载的过程
    ReleaseToLoad,//释放到加载的过程
    LoadReleased,//加载释放

    PullUpCanceled,//上拉取消
    Loading,//加载中
    LoadFinish,//加载完成
    ;

//    public boolean isAnimating() {
//        return this == Refreshing ||
//                this == Loading;
//    }

    public boolean isDraging() {
        return ordinal() >= PullDownToRefresh.ordinal()
                && ordinal() <= ReleaseToLoad.ordinal()
                && this != PullDownCanceled
                && this != PullUpCanceled;
    }

//    public boolean isDragingHeader() {
//        return this == PullDownToRefresh ||
//                this == ReleaseToRefresh;
//    }
//
//    public boolean isDragingFooter() {
//        return this == PullToUpLoad ||
//                this == ReleaseToLoad;
//    }

    public boolean isHeader() {
        return (ordinal() & 1) == 1;
    }

    public boolean isFooter() {
        return (ordinal() & 1) == 0 && ordinal() > 0;
    }

}