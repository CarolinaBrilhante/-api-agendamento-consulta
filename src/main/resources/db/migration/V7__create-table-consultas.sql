CREATE TABLE consultas (
id bigInt not null auto_increment,
data dateTime not null,
medico_id bigInt not null,
paciente_id bigInt not null,
primary key(id),
constraint fk_consultas_medico_id foreign key(medico_id) references medicos(id),
constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id)
);
