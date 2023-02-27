import React, { useEffect, FC, useState, useRef } from 'react';
import SockJS from 'sockjs-client';
import * as StompJS from '@stomp/stompjs';
import { getheader } from '@utils/axios';
import FindSpaceWithSeatsRs from '@domain/rs/space/FindSpaceWithSeatsRs';

interface SocketJsProps {
  spaceData?: FindSpaceWithSeatsRs;
}

const SocketJs: FC<SocketJsProps> = ({ spaceData }) => {
  const [isClientConnect, setIsClientConnect] = useState(false);
  const client = useRef<StompJS.Client | null>(null);

  const connect = () => {
    client.current = new StompJS.Client({
      // brokerURL: 'ws://10.111.3.121:8080/ws-miseat/ws',
      webSocketFactory: () => new SockJS(`${process.env.DOMAIN}/ws-miseat`),
      connectHeaders: getheader(),
      debug: function (err) {
        console.log(err);
      },
      reconnectDelay: 5000, //자동 재 연결
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        setIsClientConnect(true);
      },
      onStompError: (frame) => {
        console.error(frame);
      },
    });

    client.current?.activate();
  };

  const disconnect = () => {
    client.current?.deactivate();
  };

  const publish = () => {
    if (!client.current?.connected) {
      return;
    }

    client.current?.publish({
      destination: '/pub/reservation',
      body: JSON.stringify({
        reservations: [
          {
            date: '2023-02-10', // 현재 날짜로 변경
            seatNumber: 1,
          },
        ],
      }),
      headers: getheader(),
    });
  };

  //실시간 팀좌석예약 결과 수신
  const subTeamReservationRs = (spaceData?: FindSpaceWithSeatsRs) => {
    if (spaceData && isClientConnect) {
      client.current?.subscribe(
        `/sub/team/${spaceData?.teamCode}`,
        (rs: any) => {
          console.log(rs);
        },
        getheader()
      );
    }
  };

  //좌석 예약 신청 결과 수신
  const subSeatReservationRs = () => {
    if (isClientConnect) {
      client.current?.subscribe(
        `/worker/reservation/result`,
        (rs: any) => {
          console.log(rs);
        },
        getheader()
      );
    }
  };

  useEffect(() => {
    connect();
    return () => disconnect();
  }, []);

  useEffect(() => {
    subTeamReservationRs(spaceData);
    subSeatReservationRs();
  }, [spaceData, isClientConnect]);

  return <button onClick={publish}>버튼</button>;
};

export default SocketJs;
