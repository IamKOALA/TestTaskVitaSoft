INSERT INTO public.roles(
    id, name)
VALUES (1, 'USER');

INSERT INTO public.roles(
    id, name)
VALUES (2, 'OPERATOR');

INSERT INTO public.roles(
    id, name)
VALUES (3, 'ADMIN');


INSERT INTO public.users
VALUES (1, 'admin', 'admin');

INSERT INTO public.users
VALUES (2, 'user', 'user');


INSERT INTO public.users_roles
VALUES (1, 3);

INSERT INTO public.users_roles
VALUES (1, 2);

INSERT INTO public.users_roles
VALUES (2, 1);