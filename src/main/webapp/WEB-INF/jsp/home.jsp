<%@ include file="common/header.jspf" %>

<div id="content">
    <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
        <div class="container-fluid">
            <button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i
                    class="fas fa-bars"></i></button>
            <form class="form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                <div class="input-group"><input class="bg-light form-control border-0 small" type="text"
                                                placeholder="Search for ...">
                    <div class="input-group-append">
                        <button class="btn btn-primary py-0" type="button"><i class="fas fa-search"></i></button>
                    </div>
                </div>
            </form>
        </div>
    </nav>
    <%--    Dashboard Start--%>
    <div class="container-fluid">
        <div class="d-sm-flex justify-content-between align-items-center mb-4">
            <h3 class="text-dark mb-0">Dashboard</h3><a class="btn btn-primary btn-sm d-none d-sm-inline-block"
                                                        role="button" href="/get-pdf"><i
                class="fas fa-download fa-sm text-white-50"></i>&nbsp;Generate PDF</a>
        </div>
        <%--        Dashboard end--%>

        <div class="row">
            <div class="col">
                <div class="card shadow mb-3">
                    <div class="card-header py-3">
                        <p class="text-primary m-0 font-weight-bold">Upload a Excel or CSV File</p>
                    </div>
                    <div class="card-body">
                        <form id="ajaxFileUpload" method="post" action="upload" enctype="multipart/form-data">
                            <div class="form-group">
                                <div class="form-row">
                                    <div class="col">
                                        <div class="custom-file">
                                            <input id="ajaxFile" type="file" class="custom-file-input" name="file">
                                            <label class="custom-file-label">Upload File</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary btn-sm" type="submit">Upload File</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="card shadow">
                    <div class="card-header py-3">
                        <p class="text-primary m-0 font-weight-bold">Contact Settings</p>
                    </div>
                    <div class="card-body">
                        <form>
                            <div class="form-group"><label for="address"><strong>Address</strong></label><input
                                    class="form-control" type="text" placeholder="Sunset Blvd, 38" name="address"></div>
                            <div class="form-row">
                                <div class="col">
                                    <div class="form-group"><label for="city"><strong>City</strong></label><input
                                            class="form-control" type="text" placeholder="Los Angeles" name="city">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group"><label for="country"><strong>Country</strong></label><input
                                            class="form-control" type="text" placeholder="USA" name="country"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary btn-sm" type="submit">Save&nbsp;Settings</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="text-primary font-weight-bold m-0">Today's Word</h6>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <div class="row align-items-center no-gutters">
                        <div class="col mr-2">
                            <h6 class="mb-0"><strong>Lunch meeting</strong></h6><span class="text-xs">10:30 AM</span>
                        </div>
                        <div class="col-auto">
                            <div class="custom-control custom-checkbox"><input class="custom-control-input"
                                                                               type="checkbox" id="formCheck-1"><label
                                    class="custom-control-label" for="formCheck-1"></label></div>
                        </div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row align-items-center no-gutters">
                        <div class="col mr-2">
                            <h6 class="mb-0"><strong>Lunch meeting</strong></h6><span class="text-xs">11:30 AM</span>
                        </div>
                        <div class="col-auto">
                            <div class="custom-control custom-checkbox"><input class="custom-control-input"
                                                                               type="checkbox" id="formCheck-2"><label
                                    class="custom-control-label" for="formCheck-2"></label></div>
                        </div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row align-items-center no-gutters">
                        <div class="col mr-2">
                            <h6 class="mb-0"><strong>Lunch meeting</strong></h6><span class="text-xs">12:30 AM</span>
                        </div>
                        <div class="col-auto">
                            <div class="custom-control custom-checkbox"><input class="custom-control-input"
                                                                               type="checkbox" id="formCheck-3"><label
                                    class="custom-control-label" for="formCheck-3"></label></div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>


<%@ include file="common/footer.jspf" %>