'use client';
import React, { useEffect, useState, useCallback, useRef } from 'react';
import SockJS from 'sockjs-client';
import * as StompJS from '@stomp/stompjs';

const ACCESSTOKEN =
  'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJNaVNlYXQgSXNzdWVyIiwidGVhbUNvZGUiOjc3Nzc3NywiZXhwIjo5MjIzNTM5NTYzOTk4MiwiaWF0IjoxNjc1MjcxNDM0LCJ1c2VybmFtZSI6ImtrczEwMjMifQ.CLoY0PNZlRaBeid5q-KoWeMog3RLlQWLwf9xzJgqAxc';

const SocketJs = () => {
  const client = useRef<StompJS.Client | null>(null);

  const connect = () => {
    client.current = new StompJS.Client({
      webSocketFactory: () => new SockJS(`${process.env.DOMAIN}`),
      connectHeaders: {
        Authorization: ACCESSTOKEN,
      },
      debug: function (err) {
        console.log(err);
      },

      reconnectDelay: 5000, //자동 재 연결
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        subscribeTeamSeat();
      },
      onStompError: (frame) => {
        console.error('error', frame);
        console.log(frame.headers['message']);
      },
    });
    client.current.activate();
  };

  const disconnect = () => {
    client.current?.deactivate();
  };

  /**팀원들 실시간 좌석 예약 결과 */
  const subscribeTeamSeat = () => {
    client.current?.subscribe(`/worker/topic/team`, (msg: any) => {
      console.log('받은 메세지', msg.body);
    });
  };

  /**실시간 좌석 예약 결과 */
  const subscribeSeatResult = () => {
    client.current?.subscribe(`/app/reservation/team`, (msg: any) => {
      console.log('받은 메세지', msg.body);
    });
  };

  const publish = () => {
    if (!client.current?.connected) {
      return;
    }

    client.current?.publish({
      destination: '/pub/health',
      body: JSON.stringify({ message: 'hi' }),
    });
  };

  useEffect(() => {
    connect();

    return () => disconnect();
  }, []);

  return (
    <>
      <div>
        <div id="menu">
          <p>Welcome,</p>
        </div>
        <button onClick={() => publish()}>버어튼</button>
      </div>
    </>
  );
};

export default SocketJs;
