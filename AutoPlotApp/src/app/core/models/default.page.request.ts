import {FilterModel} from './filter.model';
import {SortModel} from './sort.model';

export class DefaultPageRequest {
  pageId: number;
  pageSize: number;
  totalSize: number;
  filterModel: FilterModel;
  result ;
  sortModel: SortModel;
}
