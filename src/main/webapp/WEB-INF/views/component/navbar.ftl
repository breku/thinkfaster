<header id="mainHeader" class="navbar-fixed-top">

    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <div class="navbar-header">

                <a class="brand logo" href="/index">
                    <img src="/resources/img/shaggy_logo.png"/>
                </a>
            </div>
            <div class="navbar-inner">

                <ul class="nav navbar-nav">
                    <li class="navbar-item">
                        <a class="navbar-item-text" href="/food"><@spring.message "navbar.food"/></a>
                    </li>




                <@sec.authorize ifAllGranted="ROLE_USER">
                    <li class="navbar-item">
                        <a class="navbar-item-text" href="/user/food/my"><@spring.message "navbar.food.my"/></a>
                    </li>
                </@sec.authorize>

                </ul>


                <ul class="nav  navbar-nav pull-right">

                <@sec.authorize access="isAuthenticated()">

                    <@sec.authorize ifAllGranted="ROLE_ADMIN">
                        <li class="navbar-item">
                            <a href="/admin/settings" class="navbar-item-icon"><span
                                    class="navbar-glyph-icon glyphicon glyphicon-cog"></span></a>
                        </li>
                    </@sec.authorize>


                    <li class="navbar-item dropdown">
                        <a href="#" class="dropdown-toggle navbar-item-icon" data-toggle="dropdown"><span
                                class="navbar-glyph-icon glyphicon glyphicon-user"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/user/profile"><@spring.message "navbar.profile"/></a></li>
                            <li><a href="/logout"><@spring.message "navbar.logout"/></a></li>
                        </ul>
                    </li>
                </@sec.authorize>
                <@sec.authorize access="isAnonymous()">
                    <li class="navbar-item dropdown">
                        <a href="#" class="dropdown-toggle navbar-item-icon" data-toggle="dropdown"><span
                                class="navbar-glyph-icon glyphicon glyphicon-user"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="navbar-item"><a href="/login"><@spring.message "navbar.login"/></a></li>
                            <li class="navbar-item"><a href="/register"><@spring.message "navbar.register"/></a></li>
                        </ul>
                    </li>
                </@sec.authorize>
                </ul>
            </div>
    </nav>
    </div>
</header>
