import FindSpaceWithSeatsRs from '@domain/rs/space/FindSpaceWithSeatsRs';
import customAxios from '@utils/axios';
import { AxiosResponse } from 'axios';

export default interface SpaceRepo {
  //예약된 좌석, 맵정보 리턴.
  fetchSpace(date: string): Promise<AxiosResponse<FindSpaceWithSeatsRs>>;
}

export class RemoteSpaceRepo implements SpaceRepo {
  fetchSpace(date: string) {
    return customAxios.get('/space', { params: date });
  }
}
