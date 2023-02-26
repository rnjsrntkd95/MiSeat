import repo from '@repository';
import { useQuery } from 'react-query';

const key = 'space';

export const fetchSpace = async (date: string) => {
  const { data } = await repo.remoteSpaceRepo.fetchSpace(date);
  return data;
};
