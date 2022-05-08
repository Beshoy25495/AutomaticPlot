import {Component, OnInit, ViewChild} from '@angular/core';
import {ColDef, ValueGetterParams} from 'ag-grid-community';
import {Observable, Subscription} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {AgGridAngular} from 'ag-grid-angular';
import {ApiService} from '../core';
import {PlotModel} from '../core/models/plot.model';
import {FilterModel} from '../core/models/filter.model';
import {SortModel} from '../core/models/sort.model';
import {DefaultPageRequest} from '../core/models/default.page.request';
import {TypeEntityModel} from '../core/models/typeEntity.model';
import row from 'ag-grid-enterprise/dist/lib/excelExport/files/xml/row';
import {forEach} from 'ag-grid-community/dist/lib/utils/array';

@Component({
  selector: 'app-types-page',
  templateUrl: './types.component.html'
})
export class TypesComponent implements OnInit {

  constructor(private http: HttpClient,
              private apiService: ApiService) {

    this.typeModel.filterModel = new FilterModel();
    this.typeModel.result = [];
    this.typeModel.sortModel = new SortModel();
    this.typeModel.sortModel.noSort = true;
    //
    this.apiService.post('/type/getTypePage', this.typeModel)
      .subscribe((value) => {
        this.rowData = value.result;
      });


  }

  typeModel: DefaultPageRequest = new DefaultPageRequest();
  rowData: TypeEntityModel[];

  @ViewChild('agGrid') agGrid!: AgGridAngular;

  public defaultColDef: ColDef = {
    flex: 1,
    minWidth: 100,
  };


  columnDefs: ColDef[] = [
    {
      field: 'type', sortable: true, filter: true,
      checkboxSelection: true, cellRenderer: 'agGroupCellRenderer'
    },
    // {
    //   field: 'time', filter: true,
    //   valueGetter: function (params: ValueGetterParams) {
    //     return params.data.timeEntityList[0].time;
    //   }
    // },
    // {
    //   field: 'area', filter: true,
    //   valueGetter: function (params: ValueGetterParams) {
    //     return params.data.plotEntityList[0].area;
    //   }
    // },
  ];
  //////////////////////////////////////////////////////////


  detailCellRendererParams: any = {
    detailGridOptions: {
      columnDefs: [{field: 'area'}, {field: 'time'}],
      defaultColDef: {
        flex: 1,
      },
    },
    getDetailRowData: function (params) {

      // tslint:disable-next-line:prefer-const
      let results: Array<{ time: string, area: string }> = [];

      if ((params.data.timeEntityList != null && params.data.timeEntityList.length > 0) &&
        (params.data.plotEntityList != null && params.data.plotEntityList.length > 0)) {
        if (params.data.timeEntityList.length > params.data.plotEntityList.length) {
          for (let i = 0; i < params.data.timeEntityList.length; i++) {
            results.push({area: params.data.plotEntityList[i].area, time: params.data.timeEntityList[i].time});
          }
          params.data.results = results;
        } else {
          for (let i = 0; i < params.data.timeEntityList.length; i++) {
            results.push({area: params.data.plotEntityList[i].area, time: params.data.timeEntityList[i].time});
          }
          params.data.results = results;
        }

        params.successCallback(params.data.results);
      } else {
        if ((params.data.timeEntityList != null && params.data.timeEntityList.length > 0)) {
          params.successCallback(params.data.timeEntityList);
        }
        if ((params.data.plotEntityList != null && params.data.plotEntityList.length > 0)) {
          params.successCallback(params.data.plotEntityList);
        }
      }

      //  const results = [ ...params.data.timeEntityList, ...params.data.plotEntityList];
      // params.successCallback(params.data.results);
    },
  };


  ngOnInit(): void {
  }

  getSelectedRows(): void {
    const selectedNodes = this.agGrid.api.getSelectedNodes();
    const selectedData = selectedNodes.map(node => node.data);

    // tslint:disable-next-line:prefer-const
    let tempDataSelcetion: TypeEntityModel;
    if (selectedData != null && selectedData.length > 0) {
      tempDataSelcetion.type = selectedData[0].type;
      for (let i = 0; i < selectedData.length ; i++) {
        tempDataSelcetion.timeEntityList = selectedData[i].timeEntityList;
        tempDataSelcetion.plotEntityList = selectedData[i].plotEntityList;
      }
    }
    console.log(JSON.stringify(tempDataSelcetion));

  }


  gridReady() {
    this.apiService.post('/type/getTypePage', this.typeModel)
      .subscribe((value) => {
        this.rowData = value.result;
      });
  }
}
