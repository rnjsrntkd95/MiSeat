import FindSpaceWithSeatsRs from '@domain/rs/space/FindSpaceWithSeatsRs';
import { AxiosResponse } from 'axios';
import SpaceRepo from './SpaceRepo';

export default class SpaceMock implements SpaceRepo {
  async fetchSpace(date: string) {
    {
      return {
        data: {
          teamCode: 777777,
          reservationDate: '2023-02-08',
          seats: [
            {
              seatNumber: 1,
              x: 0,
              y: 0,
              workerName: '권구상',
            },
          ],
          ysize: 10,
          xsize: 10,
        },
      } as AxiosResponse<FindSpaceWithSeatsRs>;
    }
  }
}
