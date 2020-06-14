from random import randint

if __name__ == '__main__':
  doktori_klinike = []
  with open('doctors.txt') as f:
    for line in f:
      doktori_klinike.append(line[165])
  datetimes = set()
  offset = 0
  for i in range(50):
    doc = randint(1, 20)
    date = '2020-06-2{0}'.format(randint(0, 9))
    hour = randint(0, 23)
    time = '0' + str(hour) if hour < 10 else str(hour)
    datetime = date + ' ' + time
    if datetime in datetimes:
      offset += 1
      continue
    price = randint(5, 20) * 100
    discount = randint(0,5)
    print('insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values ({0}, \'{1}\', {2}.0, {3}, {4}, 0.{5}, 1.0);'
    .format(doc, datetime, price, doktori_klinike[doc] , 1, discount))
    print('insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values ({0}, {1}, \'{2}\', {3}, {4});'
    .format(999099-i, doc, datetime, 5, i + 1 - offset))
    print('insert into clinic_room_schedule_item (clinic_room_id, start_time) values ({0}, \'{1}\');'
    .format(doktori_klinike[doc], datetime))