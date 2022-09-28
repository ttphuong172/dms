import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {DeviceListComponent} from './device/device-list/device-list.component';
import {HttpClientModule} from "@angular/common/http";
import {DeviceComponent} from './device/device/device.component';
import {RouterModule} from "@angular/router";
import {DeviceAddComponent} from './device/device-add/device-add.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {DeviceDetailComponent} from './device/device-detail/device-detail.component';
import {DeviceDeleteComponent} from './device/device-delete/device-delete.component';
import {MatDialogModule} from "@angular/material/dialog";
import {DeviceEditComponent} from './device/device-edit/device-edit.component';
import {CampusComponent} from './campus/campus/campus.component';
import {CampusListComponent} from './campus/campus-list/campus-list.component';
import {FloorComponent} from './floor/floor/floor.component';
import {FloorListComponent} from './floor/floor-list/floor-list.component';
import {FloorDetailComponent} from './floor/floor-detail/floor-detail.component';
import {CampusDetailComponent} from './campus/campus-detail/campus-detail.component';
import {RoomComponent} from './room/room/room.component';
import {RoomListComponent} from './room/room-list/room-list.component';
import {RoomDetailComponent} from './room/room-detail/room-detail.component';
import {RoomDeviceAddComponent} from './device/room-device-add/room-device-add.component';
import {TransferListComponent} from './transfer/transfer-list/transfer-list.component';
import {AngularFireStorageModule} from "@angular/fire/compat/storage";
import {AngularFireModule} from "@angular/fire/compat";
import {environment} from "../environments/environment";
import {DeviceMantainComponent} from './device/device-mantain/device-mantain.component';
import {MantainListComponent} from './mantain/mantain-list/mantain-list.component';
import {FloorDeviceListComponent} from './floor/floor-device-list/floor-device-list.component';
import {HomeComponent} from './common/home/home.component';
import {FloorAddComponent} from './floor/floor-add/floor-add.component';
import {RoomAddComponent} from './room/room-add/room-add.component';
import {LoginComponent} from './common/login/login.component';
import {AdminGuard} from "../service/admin.guard";
import {NgxPaginationModule} from "ngx-pagination";
import {TransferAddComponent} from './transfer/transfer-add/transfer-add.component';
import {TransferComponent} from './transfer/transfer/transfer.component';
import { TransferDetailComponent } from './transfer/transfer-detail/transfer-detail.component';
import {WebcamModule} from "ngx-webcam";


@NgModule({
  declarations: [
    AppComponent,
    DeviceListComponent,
    DeviceComponent,
    DeviceAddComponent,
    DeviceDetailComponent,
    DeviceDeleteComponent,
    DeviceEditComponent,
    CampusComponent,
    CampusListComponent,
    FloorComponent,
    FloorListComponent,
    FloorDetailComponent,
    CampusDetailComponent,
    RoomComponent,
    RoomListComponent,
    RoomDetailComponent,
    RoomDeviceAddComponent,
    TransferListComponent,
    DeviceMantainComponent,
    MantainListComponent,
    FloorDeviceListComponent,
    HomeComponent,
    FloorAddComponent,
    RoomAddComponent,
    LoginComponent,
    TransferAddComponent,
    TransferComponent,
    TransferDetailComponent,

  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        MatSnackBarModule,
        MatDialogModule,
        RouterModule.forRoot([
            {path: "", component: LoginComponent},
            {
                path: "", component: HomeComponent, canActivate: [AdminGuard], children: [
                    {
                        path: "devices", component: DeviceComponent, children: [
                            {path: "", component: DeviceListComponent},
                            {path: "add", component: DeviceAddComponent},
                            {path: "add/:roomId", component: RoomDeviceAddComponent},
                            {path: "detail/:id", component: DeviceDetailComponent},
                            {path: "edit/:id", component: DeviceEditComponent},
                            {path: "mantain/:id", component: DeviceMantainComponent},
                            {path: "transfers/:id", component: TransferAddComponent},
                        ]
                    },
                    {
                        path: "campus", component: CampusComponent, children: [
                            {path: "", component: CampusListComponent},
                            {path: "detail/:id", component: CampusDetailComponent}
                        ]
                    },
                    {
                        path: "floors", component: FloorComponent, children: [
                            {path: "", component: FloorListComponent},
                            {path: "add/:campusId", component: FloorAddComponent},
                            {path: "detail/:id", component: FloorDetailComponent},
                            {path: "devices/:id", component: FloorDeviceListComponent}
                        ]
                    },
                    {
                        path: "rooms", component: RoomComponent, children: [
                            {path: "", component: RoomListComponent},
                            {path: "add/:floorId", component: RoomAddComponent},
                            {path: "detail/:id", component: RoomDetailComponent}
                        ]
                    },
                    {
                        path: "transfers", component: TransferComponent, children: [
                            {path: "", component: TransferListComponent},
                            {path: "add", component: TransferAddComponent},
                            {path: "detail/:id", component: TransferDetailComponent}
                        ],
                    },
                    {
                        path: "mantains", component: MantainListComponent
                    }
                ]
            },
        ], {useHash: true}),
        ReactiveFormsModule,
        BrowserAnimationsModule,
        AngularFireModule.initializeApp(environment.firebaseConfig),
        AngularFireStorageModule,
        NgxPaginationModule,
        FormsModule,
        WebcamModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
