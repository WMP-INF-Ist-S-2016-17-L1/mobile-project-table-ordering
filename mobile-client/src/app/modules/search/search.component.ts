import { Component, OnInit, ViewContainerRef } from "@angular/core";
import { ModalDialogOptions, ModalDialogService } from "nativescript-angular/modal-dialog";
import { RadSideDrawer } from "nativescript-ui-sidedrawer";
import * as app from "tns-core-modules/application";
import { isAndroid } from "tns-core-modules/platform";
import { SearchBar } from "tns-core-modules/ui/search-bar";
import { VenuesService } from "~/app/services/venues.service";
import { SelectLocationDialogComponent } from "./components";

@Component({
    selector: "Search",
    moduleId: module.id,
    styleUrls: ["./search.component.scss"],
    templateUrl: "./search.component.html"
})
export class SearchComponent implements OnInit {

    searchPhrase: string;

    constructor(
        private venuesService: VenuesService,
        private modalService: ModalDialogService,
        private viewContainerRef: ViewContainerRef) { }

    ngOnInit(): void {
        // Init your component properties here.
    }

    onDrawerButtonTap(): void {
        const sideDrawer = <RadSideDrawer>app.getRootView();
        sideDrawer.showDrawer();
    }

    searchBarLoaded(args) {
        const searchBar = <SearchBar>args.object;
        searchBar.dismissSoftInput();

        if (isAndroid) {
            searchBar.android.clearFocus();
        }

        searchBar.text = "";
    }

    onSubmit(args) {
        const searchBar = <SearchBar>args.object;
        this.searchPhrase = searchBar.text;
        this.venuesService.setUserVenueQuery(this.searchPhrase);
        searchBar.dismissSoftInput();
    }

    showSelectLocationDialog() {
        const options: ModalDialogOptions = {
            viewContainerRef: this.viewContainerRef
        };

        this.modalService.showModal(SelectLocationDialogComponent, options);
    }

}
